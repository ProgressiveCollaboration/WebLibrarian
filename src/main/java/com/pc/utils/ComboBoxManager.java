package com.pc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboBoxManager
{
    
    public static List<String> getGenres()
    {
        String [] genres = {"R&B", "Raggae", "Gospel", "Celtic", "Classical", "Pop", "Jaz", "Blues"};
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(genres));
        return list;
    }
}
