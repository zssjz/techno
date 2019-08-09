package com.jason.module;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BNC on 2019/7/9.
 */
public class MessageDTO {

    private int code;

    @Enumerated(EnumType.STRING)
    private HttpStatus status;

    private String info;

    private Object content;

    public MessageDTO() {
    }

    public MessageDTO(int code, HttpStatus status, String info) {
        this.code = code;
        this.status = status;
        this.info = info;
    }

    public MessageDTO(int code, HttpStatus status, String info, Object content) {
        this.code = code;
        this.status = status;
        this.info = info;
        this.content = content;
    }

    public MessageDTO(int code, HttpStatus status, String info, Page page) {
        this.code = code;
        this.status = status;
        this.info = info;
        Map<String, Object> param = new HashMap<>();
        param.put("total", page.getTotalElements());
        param.put("size", page.getSize());
        param.put("page", page.getTotalPages());
        param.put("item", page.getContent());
        this.content = param;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
                "code=" + code +
                ", status=" + status +
                ", info='" + info + '\'' +
                ", content=" + content +
                '}';
    }
}
