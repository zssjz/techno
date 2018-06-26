package com.jason.components.dao;

import com.jason.components.model.RegionDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionDO, Integer> {
}
