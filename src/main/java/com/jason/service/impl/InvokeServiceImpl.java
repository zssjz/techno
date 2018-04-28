package com.jason.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.service.InvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by BNC on 2018/4/28.
 */
@Service
public class InvokeServiceImpl implements InvokeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String invokeLocalRestService() {
        String url = "http://localhost:8080/hello/words";
        return null;
    }
}
