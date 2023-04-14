package io.renren.modules.message.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.message.entity.LeaveDoctorMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-14 20:54:39
 */
@Mapper
public interface LeaveDoctorMessageDao extends BaseMapper<LeaveDoctorMessageEntity> {

    IPage<LeaveDoctorMessageEntity> queryLeaveMessage(IPage<LeaveDoctorMessageEntity> page,@Param("ew") QueryWrapper<LeaveDoctorMessageEntity> ew);
}
