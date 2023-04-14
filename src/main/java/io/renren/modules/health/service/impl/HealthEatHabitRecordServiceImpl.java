package io.renren.modules.health.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.health.dao.HealthEatHabitRecordDao;
import io.renren.modules.health.entity.HealthEatHabitRecordEntity;
import io.renren.modules.health.service.HealthEatHabitRecordService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("healthEatHabitRecordService")
public class HealthEatHabitRecordServiceImpl extends ServiceImpl<HealthEatHabitRecordDao, HealthEatHabitRecordEntity> implements HealthEatHabitRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");

        String mealType = (String) params.get("mealType");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        IPage<HealthEatHabitRecordEntity> page = this.baseMapper.queryAllEatHabitRecord(
                new Query<HealthEatHabitRecordEntity>().getPage(params),
                new QueryWrapper<HealthEatHabitRecordEntity>()
                .eq("1","1")
                .eq("a.user_id",userId)
                        .eq(StrUtil.isNotBlank(mealType),"a.meal_type",mealType)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.create_time", startTime, endTime)
                .orderByDesc("a.create_time")
        );

        return new PageUtils(page);
    }

}
