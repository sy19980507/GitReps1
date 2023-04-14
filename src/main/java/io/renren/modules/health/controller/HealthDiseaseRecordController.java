package io.renren.modules.health.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.health.entity.HealthDiseaseRecordEntity;
import io.renren.modules.health.service.HealthDiseaseRecordService;
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
 * @date 2023-03-13 16:14:23
 */
@RestController
@RequestMapping("health/healthdiseaserecord")
public class HealthDiseaseRecordController extends AbstractController {
    @Autowired
    private HealthDiseaseRecordService healthDiseaseRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:healthdiseaserecord:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("userId",getUserId());
        PageUtils page = healthDiseaseRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:healthdiseaserecord:info")
    public R info(@PathVariable("id") Long id){
		HealthDiseaseRecordEntity healthDiseaseRecord = healthDiseaseRecordService.getById(id);

        return R.ok().put("healthDiseaseRecord", healthDiseaseRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:healthdiseaserecord:save")
    public R save(@RequestBody HealthDiseaseRecordEntity healthDiseaseRecord){
        healthDiseaseRecord.setUserId(getUserId());
        healthDiseaseRecord.setCreateTime(new Date());
		healthDiseaseRecordService.save(healthDiseaseRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:healthdiseaserecord:update")
    public R update(@RequestBody HealthDiseaseRecordEntity healthDiseaseRecord){
		healthDiseaseRecordService.updateById(healthDiseaseRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:healthdiseaserecord:delete")
    public R delete(@RequestBody Long[] ids){
		healthDiseaseRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
