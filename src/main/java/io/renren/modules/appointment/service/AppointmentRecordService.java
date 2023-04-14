package io.renren.modules.appointment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.appointment.entity.AppointmentRecordEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-14 13:37:11
 */
public interface AppointmentRecordService extends IService<AppointmentRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils doctorviewlist(Map<String, Object> params);
}

