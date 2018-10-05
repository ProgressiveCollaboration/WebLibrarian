package com.pc.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String LastName;
	private String gender;
	private String aka;
	private String emailAddress;
	private String website;
	private String imageURL;
	private LocalDate dateOfBirth;

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
		return "Person [firstName=" + firstName + ", LastName=" + LastName + ", gender=" + gender + ", aka=" + aka
				+ ", emailAddress=" + emailAddress + ", website=" + website + ", imageURL=" + imageURL
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
}
