package io.renren.modules.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.message.entity.LeaveDoctorMessageEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-14 20:54:39
 */
public interface LeaveDoctorMessageService extends IService<LeaveDoctorMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

