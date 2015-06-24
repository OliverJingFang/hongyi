package com.cn.util.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cn.modle.RailwayStation;

public class RailwayStationDatabaseImpl implements RailwayStationDatabase {
    
    DatabaseHelper mRailwayDatabaseHelper;
    
    public RailwayStationDatabaseImpl(Context mContext) {
        if (mRailwayDatabaseHelper == null) {
            mRailwayDatabaseHelper = new DatabaseHelper(mContext);
        }
    }
    
    @Override
    public boolean insertStation(RailwayStation station) {
        SQLiteDatabase sDb = mRailwayDatabaseHelper.getReadableDatabase();
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("StationCode", station.StationCode);
        mContentValues.put("StationName", station.StationName);
        mContentValues.put("stationPinyin", station.stationPinyin);
        mContentValues.put("LocationCity", station.LocationCity);
        long s = sDb.insert(DatabaseHelper.table_NAME, null, mContentValues);
        
        if (s == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    @Override
    public boolean updateStation(RailwayStation station) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public RailwayStation getStation(int ID) {
        SQLiteDatabase sDb = mRailwayDatabaseHelper.getWritableDatabase();
        RailwayStation mRailwayStation = new RailwayStation();
        Cursor mCursor = null;
        try {
            mCursor = sDb.query(DatabaseHelper.table_NAME, null, "ID=" + ID, null, null, null, null);
            if (mCursor.moveToNext()) {
                mRailwayStation.StationCode = mCursor.getString(mCursor.getColumnIndex("StationCode"));
                mRailwayStation.StationName = mCursor.getString(mCursor.getColumnIndex("StationName"));
                mRailwayStation.stationPinyin = mCursor.getString(mCursor.getColumnIndex("stationPinyin"));
                mRailwayStation.LocationCity = mCursor.getString(mCursor.getColumnIndex("LocationCity"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (mCursor != null) {
                mCursor.close();
            }
            if (sDb != null) {
                sDb.close();
            }
        }
        return mRailwayStation;
    }
    
    @Override
    public ArrayList<RailwayStation> getAllStations() {
        ArrayList<RailwayStation> stationList = new ArrayList<RailwayStation>();
        SQLiteDatabase sDb = mRailwayDatabaseHelper.getWritableDatabase();
        Cursor mCursor = null;
        try {
            mCursor = sDb.query(true, DatabaseHelper.table_NAME, null, null, null, null, null, null, null);
            while (mCursor.moveToNext()) {
                RailwayStation mRailwayStation = new RailwayStation();
                mRailwayStation.StationCode = mCursor.getString(mCursor.getColumnIndex("StationCode"));
                mRailwayStation.StationName = mCursor.getString(mCursor.getColumnIndex("StationName"));
                mRailwayStation.stationPinyin = mCursor.getString(mCursor.getColumnIndex("stationPinyin"));
                mRailwayStation.LocationCity = mCursor.getString(mCursor.getColumnIndex("LocationCity"));
                stationList.add(mRailwayStation);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (mCursor != null) {
                mCursor.close();
            }
            if (sDb != null) {
                sDb.close();
            }
        }
        return stationList;
    }
    
}
