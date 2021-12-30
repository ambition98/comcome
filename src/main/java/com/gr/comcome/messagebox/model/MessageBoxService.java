package com.gr.comcome.messagebox.model;

import java.util.List;

import com.gr.comcome.common.SearchVO;

public interface MessageBoxService {
	
	 int selectTotalRecord(SearchVO searchVo);
	 public List<MessageBoxVO> selectAll(SearchVO searchVo);
}
