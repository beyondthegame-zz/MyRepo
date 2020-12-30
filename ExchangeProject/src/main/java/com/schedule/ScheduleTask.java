package com.schedule;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bean.entity.clean.CleanBasic;
import com.bean.entity.igt.IgtTaskBasic;
import com.config.DbContextHolder;
import com.enums.DBTypeEnum;
import com.repository.CleanBasicRepository;
import com.repository.IgtBasicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 定时任务demo
 */

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class ScheduleTask {

    @Autowired
    private CleanBasicRepository cleanBasicRepository;

    @Autowired
    private IgtBasicRepository igtBasicRepository;

//    @NacosValue(value = "${exchange.taskHandleItem}", autoRefreshed = true)
//    private String taskHandleItem;

    //3.添加定时任务
//    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());
        log.info("执行静态定时任务时间, {}", LocalDateTime.now());
        DbContextHolder.setDbType(DBTypeEnum.db1);

        IgtTaskBasic igtTaskBasic = igtBasicRepository.selectOne(Wrappers.<IgtTaskBasic>lambdaQuery()
                .eq(IgtTaskBasic::getTaskHandleItem, "11220000MB15280770200017203000001"));

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        igtTaskBasic.setUpdateTime(currentTime);

        igtBasicRepository.update(igtTaskBasic, Wrappers.<IgtTaskBasic>lambdaQuery()
                .eq(IgtTaskBasic::getTaskHandleItem, "11220000MB15280770200017203000001"));
        log.info("current time : {}", currentTime);
    }
}
