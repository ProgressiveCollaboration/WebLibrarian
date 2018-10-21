package com.pc.utils;

import com.pc.db.MDB;
import com.pc.entity.Publication;

import java.util.List;

public class Tester
{
    public static void main(String[] args)
    {
        MDB.startDB();
        List<Publication> allItems = MDB.getPublications(0, 50, "createdDate", true);
        System.out.println(allItems.get(0));
        MDB.stopDB();
    }
}
