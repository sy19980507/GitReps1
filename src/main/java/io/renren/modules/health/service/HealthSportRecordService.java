package io.renren.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.health.entity.HealthSportRecordEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 15:25:24
 */
public interface HealthSportRecordService extends IService<HealthSportRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> getSportTimeEchart(Map<String, Object> params);
}

