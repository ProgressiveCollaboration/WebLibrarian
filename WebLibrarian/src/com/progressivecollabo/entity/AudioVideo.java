package com.progressivecollabo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.progressivecollabo.enums.AudioVideoType;
import com.progressivecollabo.model.MetaData;
import com.progressivecollabo.model.Pricing;

@Entity(value = "audiovideo", noClassnameStored = true)
public class AudioVideo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private LocalDate releaseDate;
	private String title;
	private String description;
	private MetaData metaData;
	private AudioVideoType audioVideoType;
	@Embedded
	private List<Pricing> pricinginformation = new ArrayList<Pricing>(1);

	@Override
	public String IdPrefix() {
		return "AV";
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public AudioVideoType getAudioVideoType() {
		return audioVideoType;
	}

	public List<Pricing> getPricinginformation() {
		return pricinginformation;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public void setAudioVideoType(AudioVideoType audioVideoType) {
		this.audioVideoType = audioVideoType;
	}

	public void setPricinginformation(List<Pricing> pricinginformation) {
		this.pricinginformation = pricinginformation;
	}

}
