package com.cn.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final int DB_VERSION = 1;
    
    public static final String DB_NAME = "railwaystation.db";
    
    public static final String table_NAME = "railwaystation";
    
    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    
    public DatabaseHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Sql = "CREATE TABLE " + table_NAME
                     + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT,StationCode CHAR(50) NOT NULL,StationName CHAR(50) NOT NULL,LocationCity CHAR(50) NOT NULL,stationPinyin INT NOT NULL);";
        db.execSQL(Sql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion< newVersion){
            //从版本1到版本2时，增加了一个字段 desc
            String sql = "alter table ["+table_NAME+"] add [desc] nvarchar(300)";
            db.execSQL(sql);
        }
    }
    
}
