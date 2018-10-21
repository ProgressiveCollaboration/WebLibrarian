package com.pc.utils;

import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public interface GetLanguages
{
    static SortedSet<String> getLanguages()
    {
        //List<String> languages  = new ArrayList<>();
        SortedSet<String> languages = new TreeSet<String>();
        String[] isoLanguages = Locale.getISOLanguages();
        for (int i = 0; i < isoLanguages.length; i++)
        {
            Locale loc = new Locale(isoLanguages[i]);
            languages.add(loc.getDisplayLanguage());
        }
        return languages;
    }
}
