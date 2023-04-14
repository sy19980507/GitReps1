package io.renren.modules.appointment.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.appointment.dao.AppointmentRecordDao;
import io.renren.modules.appointment.entity.AppointmentRecordEntity;
import io.renren.modules.appointment.service.AppointmentRecordService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("appointmentRecordService")
public class AppointmentRecordServiceImpl extends ServiceImpl<AppointmentRecordDao, AppointmentRecordEntity> implements AppointmentRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        IPage<AppointmentRecordEntity> page = this.baseMapper.queryAllRecord(
                new Query<AppointmentRecordEntity>().getPage(params),
                new QueryWrapper<AppointmentRecordEntity>()
                .eq("1",1).eq("a.user_id",userId)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.appoint_time", startTime, endTime)
                .orderByDesc("a.appoint_time")
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils doctorviewlist(Map<String, Object> params) {
        Long userId = (Long) params.get("userId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        IPage<AppointmentRecordEntity> page = this.baseMapper.queryAllRecord(
                new Query<AppointmentRecordEntity>().getPage(params),
                new QueryWrapper<AppointmentRecordEntity>()
                        .eq("1",1).eq("a.doctor_id",userId)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.appoint_time", startTime, endTime)
                        .orderByDesc("a.appoint_time")
        );

        return new PageUtils(page);
    }

}
