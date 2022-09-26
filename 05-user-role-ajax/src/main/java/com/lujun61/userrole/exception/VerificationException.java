package com.lujun61.userrole.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class VerificationException extends AuthenticationException {

    public VerificationException() {
        super("认证失败");
    }

    public VerificationException(String detail) {
        super(detail);
    }

    public VerificationException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
