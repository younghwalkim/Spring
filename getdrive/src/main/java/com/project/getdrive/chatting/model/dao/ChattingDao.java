package com.project.getdrive.chatting.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.getdrive.chatting.vo.Message;
import com.project.getdrive.search.model.vo.Search;


@Repository
public class ChattingDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int insertMessage(Message msg) {
        return sqlSessionTemplate.insert("chatting.insertMessage", msg);
    }

	public Message selectChatRequest(String memberID) {
		return sqlSessionTemplate.selectOne("chatting.selectChatRequest", memberID);
	}

	public int deleteMessage(Message message) {
		return sqlSessionTemplate.delete("chatting.deleteMessage", message);
	}

	public int selectReceiver(String memberID) {
		return sqlSessionTemplate.selectOne("chatting.selectReceiver", memberID);
	}

	public int selectChatRequestCount(String name) {
		return sqlSessionTemplate.selectOne("chatting.selectChatRequestCount", name);
	}

	public ArrayList<Message> myChatList(String name) {
		List<Message> list = sqlSessionTemplate.selectOne("chatting.myChatList", name); 
		return (ArrayList<Message>)list;
	}

}