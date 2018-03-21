package com.quyon.Entity;

import com.quyon.CustomerException.RouteNotTrueException;

public class Route {
    /*航线类型
    * 根据限额表获得
    * domestic的值不能与international相同
    * domestic的值不能与arriveUSA同时为true
    * */
    private boolean domestic;
    private boolean international;
    private boolean arriveUSA;

    public Route(boolean _domestic,boolean _international,boolean _arriveUSA) throws RouteNotTrueException{
        if (_domestic == _international)
            throw new RouteNotTrueException("domestic的值不能与international相同");
        if (_domestic==_arriveUSA && _arriveUSA)
            throw new RouteNotTrueException("domestic的值不能与arriveUSA同时为true");

        domestic = _domestic;
        international = _international;
        arriveUSA = _arriveUSA;
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
