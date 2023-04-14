package io.renren.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.health.entity.HealthUserInfoEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 18:21:26
 */
public interface HealthUserInfoService extends IService<HealthUserInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryalluserlist(Map<String, Object> params);

    HealthUserInfoEntity getHealthUserInfoByUserId(Long userId);
}

