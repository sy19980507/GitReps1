package io.renren.modules.health.service.impl;

import cn.hutool.core.util.StrUtil;
import io.renren.modules.health.dao.HealthUserInfoDao;
import io.renren.modules.health.entity.HealthUserInfoEntity;
import io.renren.modules.health.service.HealthUserInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("healthUserInfoService")
public class HealthUserInfoServiceImpl extends ServiceImpl<HealthUserInfoDao, HealthUserInfoEntity> implements HealthUserInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Long userId = (Long) params.get("userId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        IPage<HealthUserInfoEntity> page = this.baseMapper.queryCurrentUserInfo(
                new Query<HealthUserInfoEntity>().getPage(params),
                new QueryWrapper<HealthUserInfoEntity>()
                .eq("1",1)
                .eq("a.user_id",userId)
                        .between(!StrUtil.isAllBlank(startTime, endTime), "a.create_time", startTime, endTime)
                .orderByDesc("a.create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryalluserlist(Map<String, Object> params) {

        String username = (String) params.get("username");
        IPage<HealthUserInfoEntity> page = this.baseMapper.queryalluserlist(
                new Query<HealthUserInfoEntity>().getPage(params),
                new QueryWrapper<HealthUserInfoEntity>()
                        .eq("1",1)
                        .like(StrUtil.isNotBlank(username),"b.username",username)
                        .orderByDesc("a.create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public HealthUserInfoEntity getHealthUserInfoByUserId(Long userId) {

        HealthUserInfoEntity healthUserInfoEntity = this.baseMapper.getHealthUserInfoByUserId(userId);
        return healthUserInfoEntity;
    }

}
