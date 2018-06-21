package com.jason.dto;

/**
 * Created by Jason on 2018/4/18.
 */
public class MessageDTO {

    private Integer state;

    private Integer code;

    private String info;

    private Object content;

    public MessageDTO() {
    }

    public MessageDTO(String info) {
        this.info = info;
    }

    public MessageDTO(Integer state, Integer code, String info, Object content) {
        this.state = state;
        this.code = code;
        this.info = info;
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", content=" + content +
                '}';
    }
}
