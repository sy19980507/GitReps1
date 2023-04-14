package io.renren.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.health.entity.HealthDiseaseRecordEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 16:14:23
 */
public interface HealthDiseaseRecordService extends IService<HealthDiseaseRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

