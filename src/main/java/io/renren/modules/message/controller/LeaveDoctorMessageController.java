package io.renren.modules.message.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.message.entity.LeaveDoctorMessageEntity;
import io.renren.modules.message.service.LeaveDoctorMessageService;
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
 * @date 2023-03-14 20:54:39
 */
@RestController
@RequestMapping("message/leavedoctormessage")
public class LeaveDoctorMessageController extends AbstractController {
    @Autowired
    private LeaveDoctorMessageService leaveDoctorMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:leavedoctormessage:list")
    public R list(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = leaveDoctorMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:leavedoctormessage:info")
    public R info(@PathVariable("id") Long id){
		LeaveDoctorMessageEntity leaveDoctorMessage = leaveDoctorMessageService.getById(id);

        return R.ok().put("leaveDoctorMessage", leaveDoctorMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:leavedoctormessage:save")
    public R save(@RequestBody LeaveDoctorMessageEntity leaveDoctorMessage){
        leaveDoctorMessage.setCreateTime(new Date());
        leaveDoctorMessage.setUserId(getUserId());
		leaveDoctorMessageService.save(leaveDoctorMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:leavedoctormessage:update")
    public R update(@RequestBody LeaveDoctorMessageEntity leaveDoctorMessage){
		leaveDoctorMessageService.updateById(leaveDoctorMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:leavedoctormessage:delete")
    public R delete(@RequestBody Long[] ids){
		leaveDoctorMessageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
