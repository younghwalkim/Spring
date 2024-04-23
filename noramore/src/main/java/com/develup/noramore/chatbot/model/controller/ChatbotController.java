package com.develup.noramore.chatbot.model.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatbotController {

	private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);
	private static String secretKey = "Y21yZ2plemtGSEtreWd3WVBTVm9pU0NVVEZPekJPRmM=";
	 private static String apiUrl = "https://j5cxj7pqcm.apigw.ntruss.com/custom/v1/14061/9a6d432b0d421824938597fd9ce152d53fbf9bc590314d8e5013fc5cfe6e3b0d";
	
	 /*
	 * @Autowired private ChatbotService chatbotService;
	 */

	@RequestMapping("chatbot.do")
	public String selectChatbot() {
		return "chatbot/chatbot";
	}

	
}
