package io.renren.modules.appointment.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.appointment.entity.AppointmentRecordEntity;
import io.renren.modules.appointment.service.AppointmentRecordService;
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
 * @date 2023-03-14 13:37:11
 */
@RestController
@RequestMapping("appointment/appointmentrecord")
public class AppointmentRecordController extends AbstractController {
    @Autowired
    private AppointmentRecordService appointmentRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:appointmentrecord:list")
    public R list(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = appointmentRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/doctorviewlist")
//    @RequiresPermissions("generator:appointmentrecord:list")
    public R doctorviewlist(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = appointmentRecordService.doctorviewlist(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:appointmentrecord:info")
    public R info(@PathVariable("id") Long id){
		AppointmentRecordEntity appointmentRecord = appointmentRecordService.getById(id);

        return R.ok().put("appointmentRecord", appointmentRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:appointmentrecord:save")
    public R save(@RequestBody AppointmentRecordEntity appointmentRecord){
        System.out.println(appointmentRecord);
        appointmentRecord.setCreateTime(new Date());
        appointmentRecord.setUserId(getUserId());
        appointmentRecord.setState(0);
		appointmentRecordService.save(appointmentRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:appointmentrecord:update")
    public R update(@RequestBody AppointmentRecordEntity appointmentRecord){
		appointmentRecordService.updateById(appointmentRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:appointmentrecord:delete")
    public R delete(@RequestBody Long[] ids){
		appointmentRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 完成这次预约
     */
    @RequestMapping("/accomplish/{id}")
//    @RequiresPermissions("generator:appointmentrecord:info")
    public R accomplish(@PathVariable("id") Long id){
        System.out.println(id);
        AppointmentRecordEntity appointmentRecord = appointmentRecordService.getById(id);
        appointmentRecord.setState(1);
        appointmentRecord.setCompleteTime(new Date());
        appointmentRecordService.updateById(appointmentRecord);
        return R.ok();
    }


    /**
     * 完成这次预约
     */
    @RequestMapping("/cancel/{id}")
//    @RequiresPermissions("generator:appointmentrecord:info")
    public R cancel(@PathVariable("id") Long id){
        System.out.println(id);
        AppointmentRecordEntity appointmentRecord = appointmentRecordService.getById(id);
        appointmentRecord.setState(2);
        appointmentRecordService.updateById(appointmentRecord);
        return R.ok();
    }

}
