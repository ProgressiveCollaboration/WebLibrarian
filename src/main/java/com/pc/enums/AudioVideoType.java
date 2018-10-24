package com.pc.enums;

/**
 * AudioVideo Categories
 */
public enum AudioVideoType {
	UNKNOWN, CASSETTE, VHS, CD, DVD, BLUE_RAY, E_BOOK, AUDIO_BOOK;

	public static String getDisplayText(AudioVideoType i) {
		switch (i) {
		case CASSETTE:
			return "Audio Cassette";
		case VHS:
			return "VHS Video";
		case CD:
			return "Audio CD";
		case BLUE_RAY:
			return "Blueray Video";
		case E_BOOK:
			return "E-Book";
		case AUDIO_BOOK:
			return "Audio Book";
		default:
			return "Unknown";
		}
	}
}