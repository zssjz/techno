package com.jason.components.dao;

import com.jason.TechnoApplication;
import com.jason.components.model.RegionDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testRegionRepository() {
        Sort sort = new Sort(Sort.Direction.DESC, "provinceId");
        Pageable pageable = new PageRequest(1, 10, sort);
        Page<RegionDO> page = regionRepository.findAll(pageable);
        System.out.println(page);
//        List<RegionDO> list = regionRepository.findAll();
//        System.out.println(list.size());
//        System.out.println(list);
    }


}