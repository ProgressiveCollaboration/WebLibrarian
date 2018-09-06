package com.progressivecollabo.entity;

import java.time.LocalDateTime;

/**
 * Represents a single user account.
 */
public class LibraryUser extends BaseEntity {
	private static final long serialVersionUID = 1L;

	String userid;
	String firstName;
	String MiddleName;
	String lastName;

	LocalDateTime dateOfBirth;

	String emailAddress;
	String phoneNumber;

	boolean accountBanned;
	boolean accessToRentable;
	boolean accessToConsumable;

	String emailAddressVerified;
	String emailAddressVerifiedDate;
}
