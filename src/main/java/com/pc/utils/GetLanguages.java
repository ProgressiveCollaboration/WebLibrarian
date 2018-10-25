package com.pc.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public interface GetLanguages {
	static List<String> getLanguages() {

		List<String> all = Arrays.asList(Locale.getISOLanguages()).stream().map(l -> new Locale(l).getDisplayLanguage())
				.collect(Collectors.toList());
		all.add(0, Locale.getDefault().getDisplayLanguage()); // user languages appear on top
		return all;

//		SortedSet<String> languages = new TreeSet<String>();
//		String[] isoLanguages = Locale.getISOLanguages();
//		for (int i = 0; i < isoLanguages.length; i++) {
//			Locale loc = new Locale(isoLanguages[i]);
//			languages.add(loc.getDisplayLanguage());
//		}
//		return languages;
	}
}
