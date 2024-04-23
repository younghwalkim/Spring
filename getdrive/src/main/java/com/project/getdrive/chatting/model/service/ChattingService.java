package com.project.getdrive.chatting.model.service;

import java.util.ArrayList;
import java.util.List;

import com.project.getdrive.chatting.vo.Message;


public interface ChattingService {

    int insertMessage(Message msg);

	Message selectChatRequest(String memberID);

	int deleteMessage(Message message);

	int selectReceiver(String memberID);

	int selectChatRequestCount(String name);

	// 채팅목록
	ArrayList<Message> myChatList(String name);

}