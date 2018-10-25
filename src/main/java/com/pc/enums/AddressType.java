package com.pc.enums;

/**
 * Address Categories
 */
public enum AddressType {
	MAILINGADDRESS, BILLINGADDRESS, NONE;

	public static String getDisplayText(AddressType i) {
		switch (i) {
		case MAILINGADDRESS:
			return "Mailing Address";
		case BILLINGADDRESS:
			return "Billing Address";
		default:
			return "Unknown";
		}
	}
}