package com.backend.MicroserviceMerchant.Utils;

import org.springframework.context.ApplicationContextException;

public class MerchantException extends ApplicationContextException {
    public MerchantException(String msg) {
        super(msg);
    }

    public MerchantException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
