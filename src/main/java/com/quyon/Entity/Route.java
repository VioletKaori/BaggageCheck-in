package com.quyon.Entity;

import com.quyon.CustomerException.RouteNotTrue;

public class Route {
    /*航线类型
    * 根据限额表获得
    * domestic的值不能与international相同
    * domestic的值不能与arriveUSA同时为true
    * */
    private boolean domestic;
    private boolean international;
    private boolean arriveUSA;

    public Route(boolean _domestic,boolean _international,boolean _arriveUSA) {
        domestic = _domestic;
        international = _international;
        arriveUSA = _arriveUSA;
    }

    public RouteNotTrue checkRoute(){
        if (domestic == international)
            return new RouteNotTrue("domestic的值不能与international相同");
        if (domestic==arriveUSA && arriveUSA)
            return new RouteNotTrue("domestic的值不能与arriveUSA同时为true");
        return null;

    }
    public boolean isDomestic() {
        return domestic;
    }

    public boolean isInternational() {
        return international;
    }

    public boolean isArriveUSA() {
        return arriveUSA;
    }
}
