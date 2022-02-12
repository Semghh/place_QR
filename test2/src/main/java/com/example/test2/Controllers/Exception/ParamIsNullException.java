package com.example.test2.Controllers.Exception;

public class ParamIsNullException extends ControllerException{

    public ParamIsNullException() {
        super();
    }

    public ParamIsNullException(String message) {
        super(message);
    }

    public ParamIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamIsNullException(Throwable cause) {
        super(cause);
    }

    protected ParamIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
