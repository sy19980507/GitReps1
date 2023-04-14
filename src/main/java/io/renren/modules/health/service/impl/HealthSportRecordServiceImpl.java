package io.renren.modules.health.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.health.dao.HealthSportRecordDao;
import io.renren.modules.health.entity.HealthSportRecordEntity;
import io.renren.modules.health.service.HealthSportRecordService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;





@Service("healthSportRecordService")
public class HealthSportRecordServiceImpl extends ServiceImpl<HealthSportRecordDao, HealthSportRecordEntity> implements HealthSportRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        String sportType = (String) params.get("sportType");
        IPage<HealthSportRecordEntity> page = this.baseMapper.queryAllSportRecord(
                new Query<HealthSportRecordEntity>().getPage(params),
                new QueryWrapper<HealthSportRecordEntity>()
                .eq("1","1")
                .like(StrUtil.isNotBlank(sportType),"a.sport_type",sportType)
                        .eq("a.user_id",userId)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.create_time", startTime, endTime)
                        .orderByDesc("a.create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String,Object>> getSportTimeEchart(Map<String, Object> params) {
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        Long userId = (Long) params.get("userId");
        List<Map<String,Object>> list = this.baseMapper.getSportTimeEchart(new QueryWrapper<>()
        .eq("1",1)
                .eq("a.user_id",userId)
                .between(!StrUtil.isAllBlank(startTime, endTime), "a.create_time", startTime, endTime));
        return list;
    }

}
