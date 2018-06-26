package com.jason.components.service;

import com.jason.dto.MessageDTO;

public interface RegionService {

    /**
     * 获取省份列表
     * @param current 当前页
     * @param size 数量
     * @return
     */
    MessageDTO getProvinceList(String current, String size);
}
