package io.renren.modules.health.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.health.entity.HealthSportRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface HealthSportRecordDao extends BaseMapper<HealthSportRecordEntity> {

    IPage<HealthSportRecordEntity> queryAllSportRecord(IPage<HealthSportRecordEntity> page,@Param("ew") QueryWrapper<HealthSportRecordEntity> ew);

    List<Map<String,Object>> getSportTimeEchart(@Param("ew") QueryWrapper<Object> ew);
}
