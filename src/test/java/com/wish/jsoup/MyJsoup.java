package com.wish.jsoup;

import com.wish.entity.Flower;
import com.wish.entity.FlowerCategory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Jsoup基础学习
 * @author handx 908716835@qq.com
 * @date 2017年5月11日 下午2:48:42
 *
 */

public class MyJsoup {

	private static List<FlowerCategory> getCategoryList() {

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

	public static void main(String[] args) throws InterruptedException {

		long startTime = System.currentTimeMillis(); // 获取开始时间

		System.out.println("开始爬取类别....");
		List<FlowerCategory> categoryList = getCategoryList();
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("类别爬取完毕....爬取时间：" + (endTime - startTime));

		List<Flower> flowers = new ArrayList<Flower>();

		startTime = System.currentTimeMillis(); // 获取开始时间
		for (FlowerCategory cates : categoryList) {
			System.out.println("正在爬取....路径为：" + cates.getUrl());

			try {
				Document doc = Jsoup.connect(cates.getUrl()).get();
				Elements elementsByClass = doc.getElementsByClass("cate_list");

				// 要给【FlowerCategory】修改，添加简介说明
				String cateName = elementsByClass.select("h1").text();
				// System.out.println("简介：" +
				// elementsByClass.select(".cont").text());

				Elements firstList = elementsByClass.select(".list");
				Elements elements = firstList.select("li");
				for (Element element : elements) {
					Flower flower = new Flower();
					flower.setName(element.select("a").attr("title"));
					flower.setNick(element.select("label").last().text());
					flower.setUrl(element.select("a").attr("href"));
					flower.setImgPath(element.select("img").attr("src"));
					flowers.add(flower);
				}

				System.out.println("获取成功，正在解析类别为【" + cateName + "】");

				// 如果有分页，循环解析
				int isPagination = elementsByClass.select(".pagination a").size();
				if (isPagination > 0) {

					String paginationText = elementsByClass.select(".pagination a")
							.eq(elementsByClass.select(".pagination a").size() - 2).text();

					int paginationTotal;
					if (isPagination >= 7) {
						paginationTotal = Integer.parseInt(paginationText.substring(2, paginationText.length()));
					} else {
						paginationTotal = Integer.parseInt(paginationText);
					}

					String urlCate = elementsByClass.select(".pagination a").eq(1).attr("href").split("/")[2];

					List<String> urls = new ArrayList<String>();

					System.out.println("开始解析分页路径....");
					for (int i = 1; i < paginationTotal; i++) {
						String url = "http://www.aihuhua.com/baike/" + urlCate + "/page-" + i + ".html";
						urls.add(url);
					}

					System.out.println("路径解析完毕，共" + paginationTotal + "页,开始爬取...");

					for (String url : urls) {

						doc = Jsoup.connect(url).get();
						elementsByClass = doc.getElementsByClass("cate_list");
						firstList = elementsByClass.select(".list");
						elements = firstList.select("li");
						for (Element element : elements) {
							Flower flower = new Flower();
							flower.setName(element.select("a").attr("title"));
							flower.setNick(element.select("label").last().text());
							flower.setUrl(element.select("a").attr("href"));
							flower.setImgPath(element.select("img").attr("src"));
							flowers.add(flower);
						}
					}

					System.out.println(cateName + "类别解析完毕！");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("解析完毕，共解析数据：" + flowers.size() + "条。" + "运行时间：" + (endTime - startTime));
	}


}
