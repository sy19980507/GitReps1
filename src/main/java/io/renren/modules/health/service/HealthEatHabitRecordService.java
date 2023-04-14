package io.renren.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.health.entity.HealthEatHabitRecordEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 21:29:38
 */
public interface HealthEatHabitRecordService extends IService<HealthEatHabitRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

