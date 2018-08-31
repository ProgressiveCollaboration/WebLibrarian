package com.progressivecollabo.entity;

import java.time.LocalDate;

public class AudioVideo extends BaseEntity {

	private static final long serialVersionUID = 1L;
 
	LocalDate releaseDate;
	String title;
	String description; 
	MetaData metaData;

	public static class DVD extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public DVD() {
		}
	}
	
	public static class BlueRay extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public BlueRay() {
		}
	}

	public static class AudioCD extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public AudioCD() {
		}
	}

	public static class Ebook extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public Ebook() {
		}
	}

	public static class AudioBook extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public AudioBook() {
		}
	}

	public static class VideoCassette extends AudioVideo {

		private static final long serialVersionUID = 1L;

		public VideoCassette() {
		}
	}

}
