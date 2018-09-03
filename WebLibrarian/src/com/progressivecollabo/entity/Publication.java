package com.progressivecollabo.entity;

import java.time.LocalDate;
import java.util.List;

public class Publication extends BaseEntity {

	private static final long serialVersionUID = 1L;

	String ISBN;
	String title;
	String description;
	LocalDate releaseDate;
	int numberOfPages;
	String editionNumber;
	List<Author> author;

	class Author {
		String authorName;
	}

	public static class Book extends Publication {

		private static final long serialVersionUID = 1L;

		public Book() {
			boolean paperback;
			String genre;
		}
	}

	public static class Journal extends Publication {

		private static final long serialVersionUID = 1L;

		public Journal() {
		}
	}

	public static class Magazine extends Publication {

		private static final long serialVersionUID = 1L;

		public Magazine() {

		}
	}

	public static class NewsPaper extends Publication {

		private static final long serialVersionUID = 1L;

		public NewsPaper() {

		}
	}

	public static class TextBook extends Publication {

		private static final long serialVersionUID = 1L;

		public TextBook() {

		}
	}

}
