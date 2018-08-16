package com.chenjx.redwars.rest;

import com.chenjx.redwars.constant.CityErrorInfoEnum;
import com.chenjx.redwars.domain.City;
import com.chenjx.redwars.result.GlobalErrorInfoException;
import com.chenjx.redwars.result.ResultBody;
import com.chenjx.redwars.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Api(value = "CityController", tags = {"城市操作接口"})
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "获取城市信息", notes = "根据城市名称获取城市信息")
    @ApiImplicitParam(name = "cityName", value = "城市名称", required = true, dataType = "String")
    @RequestMapping(value = "/api/citybyname", method = RequestMethod.GET)
    public ResultBody findOneCity(@RequestParam(value = "cityName", required = true) String cityName) throws GlobalErrorInfoException {
        if (StringUtils.isEmpty(cityName)) {
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        City city = cityService.findCityByName(cityName);
        return new ResultBody(city);
    }


    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public ResultBody findOneCity(@PathVariable("id") Long id) throws GlobalErrorInfoException {
        if (StringUtils.isEmpty(id)) {
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        City cityById = cityService.findCityById(id);
        return new ResultBody(cityById);
    }

    @ApiOperation(value = "获取所有城市对象", notes = "获取所有城市对象")
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public ResultBody findAllCity() {
        List<City> allCity = cityService.findAllCity();
        return new ResultBody(allCity);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public ResultBody createCity(@RequestBody City city) {
        city.setId(null);
        Boolean save = cityService.saveCity(city) > 0;
        return new ResultBody(save);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public ResultBody modifyCity(@RequestBody City city) {
        boolean update = cityService.updateCity(city) > 0;
        return new ResultBody(update);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public ResultBody modifyCity(@PathVariable("id") Long id) throws GlobalErrorInfoException {
        if (StringUtils.isEmpty(id)) {
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        boolean delete = cityService.deleteCity(id) > 0;
        return new ResultBody(delete);
    }
}
