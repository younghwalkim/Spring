package com.develup.noramore.chatting.model.service;

import com.develup.noramore.chatting.vo.Message;


public interface ChattingService {

    int insertMessage(Message msg);

	Message selectChatRequest(String memberID);

	int deleteMessage(Message message);

	int selectReceiver(String memberID);


}