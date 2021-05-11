package com.huawei.mock.device.core;

import com.huaweicloud.sdk.iot.device.IoTDevice;
import com.huaweicloud.sdk.iot.device.client.DeviceClient;
import com.huaweicloud.sdk.iot.device.client.listener.DefaultActionListenerImpl;
import com.huaweicloud.sdk.iot.device.client.requests.CommandRsp;
import com.huaweicloud.sdk.iot.device.client.requests.DeviceMessage;
import com.huaweicloud.sdk.iot.device.client.requests.ServiceProperty;
import com.huaweicloud.sdk.iot.device.devicelog.DeviceLogService;
import com.huaweicloud.sdk.iot.device.transport.ActionListener;
import org.eclipse.paho.client.mqttv3.internal.MessageCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class DeviceMockExecutor {
    private final static Logger logger = LoggerFactory.getLogger(DeviceMockExecutor.class);
    public final static String DEFAULT_SECRET = "1234567890";
    public final static String DEFAULT_SERVER_URI = "ssl://iot-mqtts.cn-north-4.myhuaweicloud.com:8883";
    public final static Supplier<CommandRsp> DEFAULT_COMMAND_FUN = () -> new CommandRsp(0);
    private static final int commandListSize = 100;


    private final String serverUri;
    private final String deviceId;
    private final String secret;
    private boolean autoReport = false;
    private long autoReportTime;
    private DeviceClient client = null;
    private final Supplier<CommandRsp> commandFun;
    // 缓存固定大小的命令数
    private final List<String> commandList = new ArrayList<String>() {
        @Override
        public boolean add(String s) {
            boolean add = super.add(s);
            if (this.size() > commandListSize) {
                this.remove(0);
            }
            return add;
        }
    };
    /**
     * 自动上报的线程执行器
     */
    private ScheduledExecutorService scheduledExecutorService;

    public DeviceMockExecutor(String deviceId) {
        this(DEFAULT_SERVER_URI, deviceId, DEFAULT_SECRET, DEFAULT_COMMAND_FUN);
    }

    public DeviceMockExecutor(String deviceId, String secret) {
        this(DEFAULT_SERVER_URI, deviceId, secret, DEFAULT_COMMAND_FUN);
    }

    /**
     * @param deviceId   设备id
     * @param commandFun 自动相应命令的函数
     */
    public DeviceMockExecutor(String deviceId, String secret, Supplier<CommandRsp> commandFun) {
        this(DEFAULT_SERVER_URI, deviceId, secret, commandFun);
    }


    public DeviceMockExecutor(String serverUri, String deviceId, String secret) {
        this(serverUri, deviceId, secret, DEFAULT_COMMAND_FUN);
    }

    /**
     * @param deviceId   设备id
     * @param commandFun 自动相应命令的函数
     */
    public DeviceMockExecutor(String serverUri, String deviceId, String secret, Supplier<CommandRsp> commandFun) {
        this.serverUri = serverUri;
        this.deviceId = deviceId;
        this.secret = secret;
        this.commandFun = commandFun;
    }

    public MockDeviceInfo getStatus() {
        return new MockDeviceInfo(
                null,
                this.serverUri,
                this.deviceId,
                this.secret,
                this.autoReport,
                this.autoReportTime,
                this.commandList
        );
    }

    /**
     * 设备上线
     *
     * @return
     */
    public int connect() {
        IoTDevice device = new IoTDevice(serverUri, deviceId, secret);

        //设置监听器接收下行
        client = device.getClient();

        client.setCommandListener((requestId, serviceId, commandName, paras) -> {
            //language=JSON
            String com = "{" +
                    "  \"requestId\": \"%s\"," +
                    "  \"serviceId\": \"%s\"," +
                    "  \"commandName\": \"%s\"," +
                    "  \"paras\": \"%s\"" +
                    "}";

            //处理命令，缓存在list中
            commandList.add(String.format(com, requestId, serviceId, commandName, paras.toString()));
            //发送命令响应
            client.respondCommand(requestId, commandFun.get());
        });
        return device.init();
    }

    /**
     * 开启自动属性上报
     */
    public synchronized void enableAutoReport(Supplier<List<ServiceProperty>> propertyFun, long autoReportTime) {
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.scheduledExecutorService.scheduleAtFixedRate(
                () -> reportProperties(propertyFun.get()),
                0, autoReportTime, TimeUnit.SECONDS);
        this.autoReportTime = autoReportTime;
        this.autoReport = true;
    }

    /**
     * 关闭自动上报
     */
    public synchronized void stopAutoReport() {
        this.scheduledExecutorService.shutdown();
        this.autoReport = false;

    }

    /**
     * 属性上报
     */
    public void reportProperties(List<ServiceProperty> properties) {
        client.reportProperties(properties, new ActionListener() {
            @Override
            public void onSuccess(Object o) {
                System.out.println(o);
            }

            @Override
            public void onFailure(Object o, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    /**
     * 消息上报
     */
    public void reportMessage(String context) {
//        client.publishRawMessage(); // 可以自定义topic，和消息体
        DeviceMessage deviceMessage = new DeviceMessage();
        deviceMessage.setContent(context);
        client.reportDeviceMessage(deviceMessage, new DefaultActionListenerImpl("消息上报"));
    }

    /**
     * 手动回复同步命令的结果
     * todo
     *
     * @return
     */
    public void syncCommandListener(String response) {

    }

    public void offline() {
        if (this.scheduledExecutorService != null) {
            this.scheduledExecutorService.shutdown();
            this.scheduledExecutorService = null;
        }
        if (client != null) {
            client.close();
        }
    }

}
