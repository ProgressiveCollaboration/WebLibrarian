package com.pc.entity;

import java.time.LocalDateTime;

/**
 * Represents a single user account.
 */

public class LibraryUser extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String userid;
	private String firstName;
	private String MiddleName;
	private String lastName;

	private LocalDateTime dateOfBirth;

	private String emailAddress;
	private String phoneNumber;

	boolean accountBanned;
	boolean accessToRentable;
	boolean accessToConsumable;

	private String emailAddressVerified;
	private String emailAddressVerifiedDate;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isAccountBanned() {
		return accountBanned;
	}

	public void setAccountBanned(boolean accountBanned) {
		this.accountBanned = accountBanned;
	}

	public boolean isAccessToRentable() {
		return accessToRentable;
	}

	public void setAccessToRentable(boolean accessToRentable) {
		this.accessToRentable = accessToRentable;
	}

	public boolean isAccessToConsumable() {
		return accessToConsumable;
	}

	public void setAccessToConsumable(boolean accessToConsumable) {
		this.accessToConsumable = accessToConsumable;
	}

	public String getEmailAddressVerified() {
		return emailAddressVerified;
	}

	public void setEmailAddressVerified(String emailAddressVerified) {
		this.emailAddressVerified = emailAddressVerified;
	}

	public String getEmailAddressVerifiedDate() {
		return emailAddressVerifiedDate;
	}

	public void setEmailAddressVerifiedDate(String emailAddressVerifiedDate) {
		this.emailAddressVerifiedDate = emailAddressVerifiedDate;
	}

}
