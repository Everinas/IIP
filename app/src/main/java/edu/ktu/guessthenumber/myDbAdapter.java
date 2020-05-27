package edu.ktu.guessthenumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import javax.xml.transform.Result;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String dif, String result)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.Difficulty,dif);
        contentValues.put(myDbHelper.Result, result);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public Integer easyWinCount(String name){
        SQLiteDatabase db = myhelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select _id from player_table2 where Difficulty = 'Easy' AND Name = '"+name+"' AND Result = 'Win'",null);
        int count = cursor.getCount();

        return count;
    }
    public Integer easyLossCount(String name){
        SQLiteDatabase db = myhelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select _id from player_table2 where Difficulty = 'Easy' AND Name = '"+name+"' AND Result = 'Loss'",null);
        int count = cursor.getCount();

        return count;
    }
    public Integer impWinCount(String name){
        SQLiteDatabase db = myhelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select _id from player_table2 where Difficulty = 'Impossible' AND Name = '"+name+"' AND Result = 'Win'",null);
        int count = cursor.getCount();

        return count;
    }
    public Integer impLossCount(String name){
        SQLiteDatabase db = myhelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select _id from player_table2 where Difficulty = 'Impossible' AND Name = '"+name+"' AND Result = 'Loss'",null);
        int count = cursor.getCount();

        return count;
    }
    public ArrayList getGames()
    {
        ArrayList<GameClass> games = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.Difficulty,myDbHelper.Result};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        while (cursor.moveToNext())
        {

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String difficulty =cursor.getString(cursor.getColumnIndex(myDbHelper.Difficulty));
            String result =cursor.getString(cursor.getColumnIndex(myDbHelper.Result));
            GameClass game = new GameClass(name,difficulty,result);
            games.add(game);
        }
        return games;
    }
    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.Difficulty,myDbHelper.Result};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  dif =cursor.getString(cursor.getColumnIndex(myDbHelper.Difficulty));
            String res = cursor.getString(cursor.getColumnIndex(myDbHelper.Result));
            buffer.append(cid+ "   " + name + "   " + dif +"   " + res + "\n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }
    public  int deleteAll()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();


        int count =db.delete(myDbHelper.TABLE_NAME ,null,null);
        return  count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "player_table2";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String Difficulty= "Difficulty";    // Column III
        private static final String Result = "Result";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ Difficulty+" VARCHAR(225),"+ Result+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}