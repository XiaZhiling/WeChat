package com.ling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ling.po.TextMessage;
import com.ling.utils.CheckUtil;
import com.ling.utils.MessageUtil;

/**
 * 微信servlet
 * @author XiaZhiling
 *
 */
public class WeChatServlet extends HttpServlet {

	private static final long serialVersionUID = -9144981917677357680L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter printWriter = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			System.out.println("echostr"+echostr);
			printWriter.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Map<String,String> map = MessageUtil.xmlToMap(req);
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
//		String createTime = map.get("CreateTime");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
//		String msgId = map.get("MsgId");
		
		String mes = null;
		if("text".equals(msgType)){
			TextMessage message = new TextMessage();
			message.setToUserName(fromUserName);
			message.setFromUserName(toUserName);
			message.setContent("您发送的消息是："+content);
			message.setCreateTime(Long.toString(new Date().getTime()));
//			message.setMsgId(msgId);
			message.setMsgType("text");
			
			mes = MessageUtil.textMessageToXml(message);
			System.out.println(mes);
		}
		out.print(mes);
	}

}
