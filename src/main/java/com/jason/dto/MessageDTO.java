package com.jason.dto;

/**
 * Created by BNC on 2018/4/18.
 */
public class MessageDTO {

    private Integer state;

    private String code;

    private String info;

    private Object content;

    public MessageDTO() {
    }

    public MessageDTO(Integer state, String code, String info, Object content) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
