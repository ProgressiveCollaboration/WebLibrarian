package com.progressivecollabo.entity;

public class Publisher extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String publisherName;
	private String publisherWebsite;
	private String copyright;

	@Override
	public String IdPrefix() {
		return "PUB";
	}

	public String getPublisherName() {
		return publisherName;
	}

	public String getPublisherWebsite() {
		return publisherWebsite;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public void setPublisherWebsite(String publisherWebsite) {
		this.publisherWebsite = publisherWebsite;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	@Override
	public String toString() {
		return "Publisher [publisherName=" + publisherName + ", publisherWebsite=" + publisherWebsite + ", copyright="
				+ copyright + "]";
	}

}
