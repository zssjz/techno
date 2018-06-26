package com.jason.components.control;

import com.jason.components.service.RegionService;
import com.jason.dto.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "地域信息")
@RestController
@RequestMapping("/region")
public class RegionControl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "获取省份列表（分页）", notes = "无条件限制")
    @GetMapping("/province/list")
    public MessageDTO getprovinceList(
            @ApiParam(value = "current", name = "当前页") @RequestParam("current") String current,
            @ApiParam(value = "pageSize", name = "数量") @RequestParam("pageSize") String pageSize) {
        MessageDTO msg = null;
        try {
            msg = regionService.getProvinceList(current, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msg = new MessageDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),0, e.getMessage(),"");
        }
        return msg;
    }
}
