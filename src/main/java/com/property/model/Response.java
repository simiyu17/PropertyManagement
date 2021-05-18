package com.property.model;

import java.io.Serializable;

public class Response implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String msg;

    public Response() { }

    public Response(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    /**
     * @return the success
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}