package cn.cy.framework.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/msg")
public class MsgController {

	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public String talk() {
		return "chat/talk";
	}

}