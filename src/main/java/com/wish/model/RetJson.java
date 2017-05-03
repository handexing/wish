package com.wish.model;

public class RetJson {

    private Integer status;
    private String message;
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private Object data;

    public RetJson() {
        super();
    }

    public RetJson(Integer status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;

    }

    public Object getData() {
        return data;
    }

    public int getDraw() {
        return draw;
    }

    public String getMessage() {
        return message;
    }


    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecordsFiltered(long count) {
        this.recordsFiltered = count;
    }

    public void setRecordsTotal(long count) {
        this.recordsTotal = count;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
