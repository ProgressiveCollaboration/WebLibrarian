package com.pc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pc.enums.AudioVideoAspectRatio;
import com.pc.enums.AudioVideoRating;

public class MetaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String season;
	private String episode;
	private String genre;
	private AudioVideoRating contentRating = AudioVideoRating.UNKNOWN;
	private AudioVideoAspectRatio aspectRatio = AudioVideoAspectRatio.UNKNOWN;

	private int mediaDurationSeconds;
	private float userRating; // 4/5 stars

	private List<String> imageURL = new ArrayList<>();

	public String getSeason() {
		return season;
	}

	public String getEpisode() {
		return episode;
	}

	public int getMediaDurationSeconds() {
		return mediaDurationSeconds;
	}

	public String getGenre() {
		return genre;
	}

	public AudioVideoRating getContentRating() {
		return contentRating;
	}

	public AudioVideoAspectRatio getAspectRatio() {
		return aspectRatio;
	}

	public List<String> getImageURL() {
		return imageURL;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public void setMediaDurationSeconds(int mediaDurationSeconds) {
		this.mediaDurationSeconds = mediaDurationSeconds;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setContentRating(AudioVideoRating contentRating) {
		this.contentRating = contentRating;
	}

	public void setAspectRatio(AudioVideoAspectRatio aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public void setImageURL(List<String> imageURL) {
		this.imageURL = imageURL;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

}
