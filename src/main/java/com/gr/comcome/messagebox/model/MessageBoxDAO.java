package com.gr.comcome.messagebox.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gr.comcome.common.SearchVO;

@Mapper
public interface MessageBoxDAO {
 int selectTotalRecord(SearchVO searchVo);
public List<MessageBoxVO> selectAll(SearchVO searchVo);

}
