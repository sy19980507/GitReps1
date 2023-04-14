package io.renren.modules.health.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.health.entity.HealthEatHabitRecordEntity;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.health.service.HealthEatHabitRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 21:29:38
 */
@RestController
@RequestMapping("health/healtheathabitrecord")
public class HealthEatHabitRecordController extends AbstractController {
    @Autowired
    private HealthEatHabitRecordService healthEatHabitRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:healtheathabitrecord:list")
    public R list(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = healthEatHabitRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:healtheathabitrecord:info")
    public R info(@PathVariable("id") Long id){
		HealthEatHabitRecordEntity healthEatHabitRecord = healthEatHabitRecordService.getById(id);

        return R.ok().put("healthEatHabitRecord", healthEatHabitRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:healtheathabitrecord:save")
    public R save(@RequestBody HealthEatHabitRecordEntity healthEatHabitRecord){
        healthEatHabitRecord.setUserId(getUserId());
        healthEatHabitRecord.setCreateTime(new Date());
		healthEatHabitRecordService.save(healthEatHabitRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:healtheathabitrecord:update")
    public R update(@RequestBody HealthEatHabitRecordEntity healthEatHabitRecord){
		healthEatHabitRecordService.updateById(healthEatHabitRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:healtheathabitrecord:delete")
    public R delete(@RequestBody Long[] ids){
		healthEatHabitRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
