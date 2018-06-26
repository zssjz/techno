package com.jason.components.service.impl;

import com.jason.components.dao.RegionRepository;
import com.jason.components.model.RegionDO;
import com.jason.components.service.RegionService;
import com.jason.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegionRepository region;

    @Override
    public MessageDTO getProvinceList(String current, String size) {
        Sort sort = new Sort(Sort.Direction.ASC, "provinceId");
        Pageable pageable = this.translatePageInfo(current, size, sort);
        MessageDTO msg = new MessageDTO();
        try {
            Page<RegionDO> page = region.findAll(pageable);
            msg.setCode(1);
            msg.setState(HttpStatus.OK.value());
            msg.setContent(page);
            msg.setInfo("");
        } catch (Exception e) {
            logger.error(e.getMessage());
            msg.setCode(0);
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setInfo(e.getMessage());
        }
        return msg;
    }

    /**
     * 创建 Pageable
     * @param current   当前页
     * @param pageSize  数量
     * @return
     */
    private Pageable translatePageInfo(String current, String pageSize, Sort sort) {
        int page = 1;
        int size = 10;
        if (current != null && !"".equals(current)) {
            page = Integer.valueOf(current);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.valueOf(pageSize);
        }
        if (sort != null) {
            return new PageRequest(page, size, sort);
        }
        return new PageRequest(page, size);
    }

}
