package com.gr.comcome.messagebox.model;

import org.springframework.stereotype.Service;

@Service
public class MessageRecvServiceImpl implements MessageRecvService {
	private final MessageRecvDAO messageRecvDAO;
	
	public MessageRecvServiceImpl(MessageRecvDAO dao) {
		this.messageRecvDAO=dao;
	}
	
	@Override
	public int insertMessageRecv(MessageRecvVO vo) {
		return messageRecvDAO.insertMessageRecv(vo);
	}
}
