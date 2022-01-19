package com.gr.comcome.messagebox.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gr.comcome.common.SearchVO;

@Service
public class MessageBoxServiceImpl implements MessageBoxService {
	private final MessageBoxDAO messgeboxDao;
	
	@Autowired
	public MessageBoxServiceImpl(MessageBoxDAO messgeboxDao) {
		this.messgeboxDao=messgeboxDao;
	}
	
	@Override
	public List<MessageBoxVO> selectAll(SearchVO searchVo) {	
		return messgeboxDao.selectAll(searchVo);
	}
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		
		return messgeboxDao.selectTotalRecord(searchVo);
	}
	@Override
	public int insertMessageBox(MessageBoxVO vo) {
		return messgeboxDao.insertMessageBox(vo);
	};

}
