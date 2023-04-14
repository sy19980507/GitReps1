package io.renren.modules.health.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.health.entity.HealthUserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 18:21:26
 */
@Mapper
public interface HealthUserInfoDao extends BaseMapper<HealthUserInfoEntity> {

    IPage<HealthUserInfoEntity> queryCurrentUserInfo(IPage<HealthUserInfoEntity> page, @Param("ew") QueryWrapper<HealthUserInfoEntity> ew);

    IPage<HealthUserInfoEntity> queryalluserlist(IPage<HealthUserInfoEntity> page, @Param("ew") QueryWrapper<HealthUserInfoEntity> ew);

    HealthUserInfoEntity getHealthUserInfoByUserId(@Param("ew") Long userId);
}
