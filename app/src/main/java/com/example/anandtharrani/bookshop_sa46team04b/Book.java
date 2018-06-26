package com.example.anandtharrani.bookshop_sa46team04b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Book extends HashMap<String, String> {

    final static String baseURL = "http://172.17.111.150/bookshop/Service1.svc/";
//    http://192.168.0.175/BookHome/Service1.svc/Books - Home
    //http://172.17.249.52/bookshop/Service1.svc/ - ISS

    public Book(String BookID, String Title, String CategoryID, String ISBN, String Author, String Stock, String Price, String Synopsis, String SWDiscount, String FinalPrice) {
        put("BookID", BookID);
        put("Title" , Title);
        put("CategoryID", CategoryID);
        put("ISBN", ISBN);
        put("Author", Author);
        put("Stock", Stock);
        put("Price", Price);
        put("Synopsis", Synopsis);
        put("SWDiscount", SWDiscount);
        put("FinalPrice", FinalPrice);
    }

    public Book(String Title, String Author, String BookID) {
        put("BookID", BookID);
        put("Title" , Title);
        put("Author", Author);
    }

    public Book(){}

    public static List<Book> list() {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Books");
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("Title"), b.getString("Author"),b.getString("BookID")));
            }
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }

    public static Book getBook(String eid) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "Books/" + eid);
        try {
            return new Book(b.getString("BookID"), b.getString("Title"), b.getString("CategoryID"),b.getString("ISBN"), b.getString("Author"), b.getString("Stock"), b.getString("Price"), b.getString("Synopsis"), b.getString("SWDiscount"), b.getString("FinalPrice"));
        } catch (Exception e) {
            Log.e("Book.getBook()", "JSONArray error");
        }
        return(null);
    }

    public static List<Book> getbyTitles(String eid) {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "title/" + eid);
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("Title"), b.getString("Author"),b.getString("BookID")));
            }
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }

    final static String imageURL = "http://172.17.111.150/bookshop/Reference/Images";
    //http://192.168.0.175/BookHome/Service1.svc/ - Home
    //"http://172.17.249.52/bookshop/Reference/Images" - ISS
    public static Bitmap getPhoto(boolean thumbnail, String id) {
        try {
            URL url = (thumbnail ? new URL(String.format("%s/%s-s.jpg",imageURL, id)) :
                    new URL(String.format("%s/%s.jpg",imageURL, id)));
            String c = String.format("%s/%s-s.jpg",imageURL, id);
            Log.i("imageurl", c);
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return(null);
    }

    public static void updateBook (Book b) {
        JSONObject jBook = new JSONObject();
        try {
            jBook.put("BookID", Integer.parseInt(b.get("BookID")));
            jBook.put("Title", b.get("Title"));
            jBook.put("CategoryID", Integer.parseInt(b.get("CategoryID")));
            jBook.put("ISBN", b.get("ISBN"));
            jBook.put("Author", b.get("Author"));
            jBook.put("Stock", Integer.parseInt(b.get("Stock")));
            jBook.put("Price", Double.parseDouble(b.get("Price")));
            jBook.put("Synopsis", b.get("Synopsis"));
            jBook.put("SWDiscount",Double.parseDouble(b.get("SWDiscount")));
            jBook.put("FinalPrice",Double.parseDouble(b.get("FinalPrice")));
        } catch (Exception e) {

        }
        String result = JSONParser.postStream(baseURL +"/Update", jBook.toString());
    }
}
