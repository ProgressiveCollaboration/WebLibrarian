package com.pc.entity;

import java.time.LocalDate;

import org.mongodb.morphia.annotations.Entity;

import com.pc.db.MDB;

@Entity(value = MDB.DB_AUTHOR, noClassnameStored = true)
public class Author extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String LastName;
	private String gender;
	private String aka;
	private String emailAddress;
	private String website;
	private String imageURL;
	private String wikiLink;
	private String bioGraphy;
	private LocalDate dateOfBirth;

	@Override
	public String IdPrefix() {
		return "PER-";
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getGender() {
		return gender;
	}

	public String getAka() {
		return aka;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getWebsite() {
		return website;
	}

	public String getImageURL() {
		return imageURL;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", LastName=" + LastName + ", gender=" + gender + ", aka=" + aka
				+ ", emailAddress=" + emailAddress + ", website=" + website + ", imageURL=" + imageURL
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

	public String getBioGraphy() {
		return bioGraphy;
	}

	public void setBioGraphy(String bioGraphy) {
		this.bioGraphy = bioGraphy;
	}

	public String getWikiLink() {
		return wikiLink;
	}

	public void setWikiLink(String wikiLink) {
		this.wikiLink = wikiLink;
	}

}
