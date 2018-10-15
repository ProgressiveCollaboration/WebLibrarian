package com.pc.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pc.db.MDB;

public class Tester_Publisher {

	public static void main(String[] args) throws Exception {

		Elements el = Jsoup.connect("https://publishers.org/about/member-list").get().select("ul")
				.select(".about-link");

		System.out.println(el.get(5).text() + " -> " + el.get(5).attr("href"));

		MDB.startDB();

		for (Element element : el) {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(element.text());
			publisher.setPublisherWebsite(element.attr("href"));
			publisher.setCopyright("");
			publisher.setCreatedBy("roomtek@gmail.com");
			publisher.save();
		}

		MDB.stopDB();
	}

}
