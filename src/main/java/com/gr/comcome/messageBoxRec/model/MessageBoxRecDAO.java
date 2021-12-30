package com.gr.comcome.messageBoxRec.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;

@Mapper
public interface MessageBoxRecDAO {
 int selectTotalRecord(SearchVO searchVo);
 public List<MessageBoxRecVO> selectAll(SearchVO searchVo);
}
