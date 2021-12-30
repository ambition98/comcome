package com.gr.comcome.messageBoxRec.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.common.SearchVO;

@Service
public class MessageBoxRecServiceImpl implements MessageBoxRecService {
	private final MessageBoxRecDAO messagerecDao;
	
	@Autowired
	public MessageBoxRecServiceImpl(MessageBoxRecDAO messagerecDao) {
		this.messagerecDao=messagerecDao;
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return messagerecDao.selectTotalRecord(searchVo);
	}

	@Override
	public List<MessageBoxRecVO> selectAll(SearchVO searchVo) {
		return messagerecDao.selectAll(searchVo);
	}
	
}
