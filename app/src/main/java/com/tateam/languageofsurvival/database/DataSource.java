package com.tateam.languageofsurvival.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tateam.languageofsurvival.entity.EnglishGuide;
import com.tateam.languageofsurvival.entity.ListTopic;

import java.util.ArrayList;


public class DataSource {
    private static DataSource instance;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private DataSource() {

    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void initIfNeeded(Context context) {
        if (this.context == null) {
            this.context = context;
        }
    }

    private void openConnection() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            AssertDatabaseOpenHelper assetDatabaseOpenHelper = new AssertDatabaseOpenHelper(context);
            sqLiteDatabase = assetDatabaseOpenHelper.openDatabase();
        }
    }

    //import db from assets if need and open connection
    public void createDatabaseIfNeed() {
        this.openConnection();
    }

    //close
    private void closeConnection() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    //destroy
    public void destroy() {
        closeConnection();
        instance = null;
    }

    //
    //query from database
    public ArrayList<EnglishGuide> getListLesson(int type) {
        ArrayList<EnglishGuide> englishGuideList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select englishsentence, othersentence,type,typeno ,recent from language where typeno ='" + type + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EnglishGuide englishGuide = new EnglishGuide();
            englishGuide.setEnglishSentence(cursor.getString(0));
            englishGuide.setFrenchSentence(cursor.getString(1));
            englishGuide.setType(cursor.getString(2));
            englishGuide.setTypeno(cursor.getInt(3));
            englishGuide.setRecent(cursor.getInt(4));
            englishGuideList.add(englishGuide);
            cursor.moveToNext();
        }
        cursor.close();
        return englishGuideList;
    }

    public ArrayList<EnglishGuide> getListRecent() {
        ArrayList<EnglishGuide> englishGuideList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from language" +
                " where  recent >0 ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EnglishGuide englishGuide = new EnglishGuide();
            englishGuide.setEnglishSentence(cursor.getString(0));
            englishGuide.setFrenchSentence(cursor.getString(1));
            englishGuide.setType(cursor.getString(2));
            englishGuide.setTypeno(cursor.getInt(3));
            englishGuide.setRecent(cursor.getInt(4));
            englishGuideList.add(englishGuide);
            cursor.moveToNext();
        }
        cursor.close();
        return englishGuideList;
    }


    public ArrayList<ListTopic> getList() {
        ArrayList<ListTopic> guideList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select distinct type from language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListTopic listTopic = new ListTopic();
            listTopic.setListName(cursor.getString(0));
            guideList.add(listTopic);
            cursor.moveToNext();
        }
        cursor.close();
        return guideList;
    }

    public void updateRecent(String englishSentence, int recent) {

        int newRecent = getMaxRecent() + 1;
        String value = newRecent + "";
        Cursor cursor = sqLiteDatabase.rawQuery("UPDATE language SET recent= ? WHERE englishsentence = ? ", new String[]{value, englishSentence});
        cursor.moveToFirst();
        cursor.close();
    }


    public int updateRecenthere(String englishSentence, int recent) {

        int newRecent = getMaxRecent() + 1;
        String value = newRecent + "";
        ContentValues newValues = new ContentValues();
        newValues.put("recent", value);
        String[] args = new String[]{englishSentence};
        long i = sqLiteDatabase.update("language", newValues, "englishsentence = ?", args);


        if (i > 0)
            return 1;  // 1 for successful
        else
            return 0;  // 0 for unsuccessful
    }

    //
    public int getMaxRecent(String englishSentence) {
        int max = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT recent FROM language where englishsentence='" + englishSentence + "'", null);

        cursor.moveToFirst();

//        max = cursor.getInt();

        cursor.close();
        return max;

    }

    public int getMaxRecent() {
        int max;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM language where recent > 0 order by recent desc limit 1", null);
        if (cursor.getCount() == 0) return 0;
        cursor.moveToFirst();

        max = cursor.getInt(4);

        cursor.close();
        return max;

    }
}
