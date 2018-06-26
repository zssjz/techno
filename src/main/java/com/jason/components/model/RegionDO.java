package com.jason.components.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "地域")
@Entity
@Table(name = "province")
public class RegionDO {

    @ApiModelProperty(value = "id", name = "主键", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "provinceId", name = "省份编码")
    @Column(name = "province_id")
    private Long provinceId;

    @ApiModelProperty(value = "provinceName", name = "省份名称")
    @Column(name = "province_name")
    private String provinceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "RegionDO{" +
                "id=" + id +
                ", provicneId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
