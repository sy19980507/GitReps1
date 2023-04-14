package io.renren.modules.health.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.health.entity.HealthDiseaseRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 16:14:23
 */
@Mapper
public interface HealthDiseaseRecordDao extends BaseMapper<HealthDiseaseRecordEntity> {

    IPage<HealthDiseaseRecordEntity> queryAllDiseaseRecord(IPage<HealthDiseaseRecordEntity> page,@Param("ew") QueryWrapper<HealthDiseaseRecordEntity> ew);
}
