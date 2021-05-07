package com.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bean.entity.clean.CleanPublicQuestions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CleanPublicQuestionRepository  extends BaseMapper<CleanPublicQuestions> {
}
