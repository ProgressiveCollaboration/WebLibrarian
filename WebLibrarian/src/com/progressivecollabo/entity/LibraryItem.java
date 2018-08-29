package com.progressivecollabo.entity;

import java.time.LocalDateTime;

/**
 * Represents a single piece/item/good
 */
public class LibraryItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	String userid; // UUID
	String firstName;
	String MiddleName;
	String lastName;

	String emailAddress;
	String phoneNumber;

	String emailAddressVerified;
	String emailAddressVerifiedDate;

	LocalDateTime dateOfBirth;
	LocalDateTime dateOfLastLogin;

	boolean accountBanned;
	boolean accessToRentable;
	boolean accessToConsumable;
}
