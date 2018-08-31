package com.progressivecollabo.entity;

import java.io.Serializable;
import java.util.List;

public class MetaData implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	int season;
	int episode;
	long mediaDurationSeconds;
	
	String genre;
	String contentRating; // PG
	float mediaRating; // 4/5 stars
	float aspectRatio;
	List<String> imageURL;
}
