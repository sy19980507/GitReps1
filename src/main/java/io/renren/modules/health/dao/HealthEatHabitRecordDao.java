package io.renren.modules.health.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.health.entity.HealthEatHabitRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 21:29:38
 */
@Mapper
public interface HealthEatHabitRecordDao extends BaseMapper<HealthEatHabitRecordEntity> {

    IPage<HealthEatHabitRecordEntity> queryAllEatHabitRecord(IPage<HealthEatHabitRecordEntity> page,@Param("ew") QueryWrapper<HealthEatHabitRecordEntity> ew);
}
