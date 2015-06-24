package com.cn.util.db;

import java.util.ArrayList;

import com.cn.modle.RailwayStation;

public interface RailwayStationDatabase {
    
    public boolean insertStation(RailwayStation station);
    
    public boolean updateStation(RailwayStation station);
    
    public RailwayStation getStation(int ID);
    
    public ArrayList<RailwayStation> getAllStations();
    
}
