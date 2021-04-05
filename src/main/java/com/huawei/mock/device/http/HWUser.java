package com.huawei.mock.device.http;

import com.sun.xml.internal.bind.v2.model.core.ReferencePropertyInfo;
import lombok.Data;
import sun.security.util.Password;

import java.util.List;

@Data
public class HWUser {
    private Auth auth;

    @Data
    public static class Auth {
        private Identity identity;
    }

    @Data
    public static class Identity {
        private List<String> methods;
        private Password password;
    }

    @Data
    public static class Password {
        private User user;
    }

    @Data
    public static class User {
        private String name;
        private String password;
        private Domain domain;
    }

    @Data
    public static class Domain {
        private String name;
    }

}
