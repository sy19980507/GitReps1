package io.renren.modules.appointment.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.appointment.entity.AppointmentRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-14 13:37:11
 */
@Mapper
public interface AppointmentRecordDao extends BaseMapper<AppointmentRecordEntity> {

    IPage<AppointmentRecordEntity> queryAllRecord(IPage<AppointmentRecordEntity> page,@Param("ew") QueryWrapper<AppointmentRecordEntity> ew);
}
