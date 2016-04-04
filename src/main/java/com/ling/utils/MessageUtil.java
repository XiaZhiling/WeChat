package com.ling.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ling.po.TextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * ΢����Ϣxml������Map�����໥ת��
 * @author XiaZhiling
 *
 */
public class MessageUtil {
	
	/**
	 * xmlת��ΪMap����
	 * @param request
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for(Element e:list){
				map.put(e.getName(), e.getText());
			}
			inputStream.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * �ı���Ϣ����ת��Ϊxml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream stream = new XStream();
		stream.alias("xml", TextMessage.class);
		return stream.toXML(textMessage);
	}

}
