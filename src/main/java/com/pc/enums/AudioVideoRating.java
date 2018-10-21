package com.pc.enums;

public enum AudioVideoRating {
	UNKNOWN, UNRATED, G, PG, PG13, R, NC17;

	public static String getDisplayText(AudioVideoRating i) {
		switch (i) {
		case UNRATED:
			return "Unknown";
		case G:
			return "G – General Audiences";
		case PG:
			return "PG – Parental Guidance Suggested";
		case PG13:
			return "PG-13 – Parents Strongly Cautioned";
		case R:
			return "R – Restricted";
		case NC17:
			return "NC-17 – Adults Only";
		default:
			return "Unknown";
		}
	}
}
