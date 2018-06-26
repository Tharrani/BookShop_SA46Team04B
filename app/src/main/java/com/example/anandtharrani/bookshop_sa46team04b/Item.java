package com.example.anandtharrani.bookshop_sa46team04b;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Item extends HashMap<String, Object> implements Serializable {

    private Book bk;

    public Book getBk() {
        return bk;
    }

    public void setBk(Book bk) {
        this.bk = bk;
    }


    public Item (){}

    public Item(Book b){
        put("Book", b);
        this.bk = b;
    }

    private static List<Item> items = new ArrayList<Item>();

    public static List<Item> getItems() {
        return items;
    }

    public static List<Book> getItemBook()
    {
        List<Book> b = new ArrayList<Book>();
        int m = items.size();
        for(int i=0; i<m;i++)
        {
            b.add(items.get(i).getBk());
        }
        return b;
    }

    private static int getIndex(Book b)
    {
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getBk().get("BookID") == b.get("BookID")){
                return i;
            }
        }
        return -1;
    }

    public static boolean isExisting(Book b)
    {
        return getIndex(b) !=-1;
    }

    public static void insert(Book bk)
    {
            if(!isExisting(bk)) {
                Item i = new Item(bk);
                items.add(i);
            }
    }

    public static  void  remove(Book b)
    {
        int i = getIndex(b);
        items.remove(i);
    }

}
