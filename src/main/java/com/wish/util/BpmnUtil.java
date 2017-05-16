package com.wish.util;

import org.activiti.engine.RepositoryService;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @Description: 解析xml工具类
 * @author handx 908716835@qq.com
 * @date 2017年5月16日 下午9:33:08
 *
 */

@Service
public class BpmnUtil {

	@Autowired
	RepositoryService repositoryService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	// 获取bpmn的key
	@SuppressWarnings({ "rawtypes" })
	public String getBpmnKey(InputStream inputStream) throws Exception {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				if (element.getName().equals("process")) {
					Iterator attrIt = element.attributeIterator();
					while (attrIt.hasNext()) {
						Attribute attr = (Attribute) attrIt.next();
						if (attr.getName().equals("id")) {
							logger.error("获取流程key:" + attr.getValue());
							return attr.getValue();
						}
					}
				}
			}
		} catch (DocumentException e) {
			throw new Exception("解析文件获取key失败！", e);
		}
		throw new Exception("解析文件获取key失败！");
	}

}
