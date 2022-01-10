package com.alsyun.common.exception;

public class MilkyWayException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MilkyWayException(String message){
        super(message);
    }

    public MilkyWayException(Throwable cause)
    {
        super(cause);
    }

    public MilkyWayException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
