package com.gr.comcome.messageBoxRec.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;

public interface MessageBoxRecService {
	 int selectTotalRecord(SearchVO searchVo);
	 public List<MessageBoxRecVO> selectAll(SearchVO searchVo);
}
