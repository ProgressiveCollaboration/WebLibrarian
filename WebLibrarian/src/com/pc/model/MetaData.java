package com.pc.model;

import java.io.Serializable;
import java.util.List;

public class MetaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private int season;
	private int episode;
	private long mediaDurationSeconds;

	private String genre;
	private String contentRating; // PG
	private float mediaRating; // 4/5 stars
	private float aspectRatio;
	private List<String> imageURL;

	public int getSeason() {
		return season;
	}

	public int getEpisode() {
		return episode;
	}

	public long getMediaDurationSeconds() {
		return mediaDurationSeconds;
	}

	public String getGenre() {
		return genre;
	}

	public String getContentRating() {
		return contentRating;
	}

	public float getMediaRating() {
		return mediaRating;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public List<String> getImageURL() {
		return imageURL;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public void setMediaDurationSeconds(long mediaDurationSeconds) {
		this.mediaDurationSeconds = mediaDurationSeconds;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public void setMediaRating(float mediaRating) {
		this.mediaRating = mediaRating;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public void setImageURL(List<String> imageURL) {
		this.imageURL = imageURL;
	}

}
