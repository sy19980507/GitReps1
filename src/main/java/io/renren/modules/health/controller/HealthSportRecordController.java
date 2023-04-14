package io.renren.modules.health.controller;

import java.util.*;

import cn.hutool.core.date.DateUtil;
import io.renren.modules.health.entity.HealthSportRecordEntity;
import io.renren.modules.health.service.HealthSportRecordService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 15:25:24
 */
@RestController
@RequestMapping("health/healthsportrecord")
public class HealthSportRecordController extends AbstractController {
    @Autowired
    private HealthSportRecordService healthSportRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:healthsportrecord:list")
    public R list(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = healthSportRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:healthsportrecord:info")
    public R info(@PathVariable("id") Long id){
		HealthSportRecordEntity healthSportRecord = healthSportRecordService.getById(id);

        return R.ok().put("healthSportRecord", healthSportRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:healthsportrecord:save")
    public R save(@RequestBody HealthSportRecordEntity healthSportRecord){
        healthSportRecord.setUserId(getUserId());
        healthSportRecord.setCreateTime(new Date());
		healthSportRecordService.save(healthSportRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:healthsportrecord:update")
    public R update(@RequestBody HealthSportRecordEntity healthSportRecord){
		healthSportRecordService.updateById(healthSportRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:healthsportrecord:delete")
    public R delete(@RequestBody Long[] ids){
		healthSportRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //构建的是每天运动时长
    @RequestMapping("/getSportTimeEchart")
    public R getSportTimeEchart(@RequestParam Map<String, Object> params){
        params.put("userId",getUserId());
        List<Map<String,Object>> list = healthSportRecordService.getSportTimeEchart(params);
        List<Date> dateList = new ArrayList<>();
        List<Double> sportTimeList = new ArrayList<>();
        list.forEach(map->{
            dateList.add(DateUtil.parse((String) map.get("time")));
            sportTimeList.add((Double) map.get("allDuration"));
        });


        return R.ok().put("dateList",dateList).put("sportTimeList",sportTimeList);
    }
}
