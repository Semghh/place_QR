package com.example.test2.Service.Exception;

public class GroupIsUsedException extends ServiceException{
    public GroupIsUsedException() {
        super();
    }

    public GroupIsUsedException(String message) {
        super(message);
    }

    public GroupIsUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupIsUsedException(Throwable cause) {
        super(cause);
    }

    protected GroupIsUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
