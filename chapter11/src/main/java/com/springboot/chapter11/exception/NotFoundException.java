package com.springboot.chapter11.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Long code;
    private String customMsg;
    public NotFoundException()
    {

    }

    public NotFoundException(Long code,String customMsg)
    {
        super();
        this.code=code;
        this.customMsg=customMsg;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }
}
