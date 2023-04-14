package io.renren.modules.message.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.message.dao.LeaveDoctorMessageDao;
import io.renren.modules.message.entity.LeaveDoctorMessageEntity;
import io.renren.modules.message.service.LeaveDoctorMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("leaveDoctorMessageService")
public class LeaveDoctorMessageServiceImpl extends ServiceImpl<LeaveDoctorMessageDao, LeaveDoctorMessageEntity> implements LeaveDoctorMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");
        String doctorId = (String) params.get("doctorId");
        IPage<LeaveDoctorMessageEntity> page = this.baseMapper.queryLeaveMessage(
                new Query<LeaveDoctorMessageEntity>().getPage(params),
                new QueryWrapper<LeaveDoctorMessageEntity>()
                .eq("1",1)
//                .eq("a.user_id",userId)
                .eq(StrUtil.isNotBlank(doctorId),"a.doctor_id",doctorId)
                .orderByDesc("a.create_time")
        );

        List<LeaveDoctorMessageEntity> records = page.getRecords();
        records.forEach(leaveDoctorMessageEntity -> {
            if(userId.equals(leaveDoctorMessageEntity.getUserId())){
                leaveDoctorMessageEntity.setFlag(1);
            }else {
                leaveDoctorMessageEntity.setFlag(0);
            }
        });

        return new PageUtils(page);
    }

}
