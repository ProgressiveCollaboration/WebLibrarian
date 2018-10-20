package com.pc.entity;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import com.pc.db.MDB;
//
//public class Tester_Authors {
//
//	public static void main(String[] args) throws Exception {
//
//		Elements el = Jsoup.connect("https://www.theguardian.com/books/list/authorsaz").get().select(".breakdown")
//				.select("li");
//
//		MDB.startDB();
//		for (Element element : el) {
//			Element s = element.getElementsByTag("a").first();
//			System.out.println(s.text() + " -> " + s.attr("href"));
//
//			String name = s.text();
//			String[] nm = name.split(" ", 2);
//
//			Author author = new Author();
//			author.setAka(name);
//			author.setFirstName(nm[0]);
//			author.setLastName(nm.length > 1 ? nm[1] : null);
//			author.setCreatedBy("roomtek@gmail.com");
//			author.setWikiLink(s.attr("href"));
//			author.setWebsite(s.attr("href"));
//			author.setModifiedBy(author.getCreatedBy());
//			author.save();
//		}
//		MDB.stopDB();
//	}
//
//}
