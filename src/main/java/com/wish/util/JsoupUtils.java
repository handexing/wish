package com.wish.util;

import com.wish.entity.FlowerCategory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtils {

	public static List<FlowerCategory> getCategoryList() {

		List<FlowerCategory> categories = new ArrayList<FlowerCategory>();
		try {
			Document doc = Jsoup.connect("http://www.aihuhua.com/baike/").get();
			Elements catelist = doc.getElementsByClass("catelist");
			Element cates = catelist.first();
			List<Node> childNodes = cates.childNodes();
			for (int i = 0; i < childNodes.size(); i++) {
				Node node = childNodes.get(i);
				List<Node> childs = node.childNodes();
				if (childs != null && childs.size() > 0) {
					FlowerCategory category = new FlowerCategory();
					for (int j = 0; j < childs.size(); j++) {
						Node child = childs.get(j);
						if ("a".equals(child.nodeName())) {
							category.setUrl(child.attr("href"));
							category.setImgPath(child.childNode(1).attr("src"));
						} else if ("h2".equals(child.nodeName())) {
							category.setName(child.attr("title"));
						}
					}
					categories.add(category);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return categories;
	}
}
