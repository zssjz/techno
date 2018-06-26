package com.jason.components.service.impl;

import com.jason.TechnoApplication;
import com.jason.components.service.RegionService;
import com.jason.dto.MessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class RegionServiceImplTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void getProvinceList() {
        MessageDTO msg = regionService.getProvinceList("1", "10");
        System.out.println(msg);
    }
}