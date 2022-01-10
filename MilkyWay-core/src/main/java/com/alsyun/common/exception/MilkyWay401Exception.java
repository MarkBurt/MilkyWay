package com.alsyun.common.exception;

/**
 * @author Markburt
 */
public class MilkyWay401Exception extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MilkyWay401Exception(String message){
        super(message);
    }

    public MilkyWay401Exception(Throwable cause)
    {
        super(cause);
    }

    public MilkyWay401Exception(String message, Throwable cause)
    {
        super(message,cause);
    }
}
