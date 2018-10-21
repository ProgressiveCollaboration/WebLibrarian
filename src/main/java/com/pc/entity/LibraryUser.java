package com.pc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a single user account.
 */
@Data
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
}
