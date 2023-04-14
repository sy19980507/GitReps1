package io.renren.modules.health.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.health.dao.HealthDiseaseRecordDao;
import io.renren.modules.health.entity.HealthDiseaseRecordEntity;
import io.renren.modules.health.service.HealthDiseaseRecordService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("healthDiseaseRecordService")
public class HealthDiseaseRecordServiceImpl extends ServiceImpl<HealthDiseaseRecordDao, HealthDiseaseRecordEntity> implements HealthDiseaseRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        IPage<HealthDiseaseRecordEntity> page = this.baseMapper.queryAllDiseaseRecord(
                new Query<HealthDiseaseRecordEntity>().getPage(params),
                new QueryWrapper<HealthDiseaseRecordEntity>()
                        .eq("1","1")
                        .eq("a.user_id",userId)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.create_time", startTime, endTime)
                        .orderByDesc("a.create_time")
        );

        return new PageUtils(page);
    }

}
