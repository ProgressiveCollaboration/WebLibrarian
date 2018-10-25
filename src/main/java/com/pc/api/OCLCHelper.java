package com.pc.api;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pc.enums.PublicationType;

public class OCLCHelper {

	private final static String baseUrl = "http://classify.oclc.org/classify2";

	public static void main(String... args) {
		List<OCLCWork> works = findByISBN("9781612680057");

		for (OCLCWork oclcWork : works) {
			System.out.println(oclcWork);
		}
	}

	public static List<OCLCWork> findByISBN(String isbn) {
		try {
			Document doc = Jsoup.connect(baseUrl + "/Classify?").data("isbn", isbn, "summary", "true").get();

			Elements works = doc.select("work");

			List<OCLCWork> publ = new ArrayList<>();
			for (Element element : works) {
				OCLCWork p = new OCLCWork();
				p.author = element.attr("author");
				p.format = PublicationType.reverse(element.attr("format"));
				p.hyear = element.attr("hyr");
				p.lyear = element.attr("lyr");
				p.owi = element.attr("owi");
				p.title = element.attr("title");
				p.wi = element.attr("wi");
				p.editions = element.attr("editions");
				p.holdings = element.attr("holdings");
				p.itemtype = element.attr("itemtype");
				publ.add(p);
			}
			return publ;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static class OCLCWork {
		public String author;
		public PublicationType format;
		public String hyear;
		public String lyear;
		public String owi;
		public String title;
		public String wi;
		public String editions;
		public String holdings;
		public String itemtype;

		@Override
		public String toString() {
			return "OCLCWork [author=" + author + ", format=" + format + ", hyear=" + hyear + ", lyear=" + lyear
					+ ", owi=" + owi + ", title=" + title + ", wi=" + wi + ", editions=" + editions + ", holdings="
					+ holdings + ", itemtype=" + itemtype + "]";
		}

	}

}
