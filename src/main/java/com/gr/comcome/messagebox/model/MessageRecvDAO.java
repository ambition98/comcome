package com.gr.comcome.messagebox.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageRecvDAO {
	public int insertMessageRecv(MessageRecvVO vo);
}
