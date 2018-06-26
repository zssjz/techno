package com.jason.components.control;

import com.jason.TechnoApplication;
import com.jason.dto.MessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class RegionControlTest {

    @Autowired
    private RegionControl regionControl;

    @Test
    public void getprovinceList() {
        MessageDTO msg = regionControl.getprovinceList("1", "10");
        System.out.println(msg);
    }
}