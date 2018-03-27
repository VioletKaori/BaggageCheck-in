package com.quyon.CustomerException;

public class BaggageExceedFree implements OutBounds {
    /**
     *
     * @param _retCd,返回错误代码
     *          1：总重量（国内航班）超出上限
     *          2：免费行李件数超出限制
     *          3：单间行李重量超出限制
     *          4：单件行李尺寸超出限制
     * @param _mesDes,
     * 返回错误信息
     * @param _exceeding,
     * 返回超出部分数值
     */
    public BaggageExceedFree(int _retCd, String _mesDes,double _exceeding){
        typeCode = _retCd;
        mesDes = _mesDes;
        exceeding = _exceeding;
    }
    private String mesDes;
    private int typeCode;
    private double exceeding;

    @Override
    public int getTypeCode() {
        return typeCode;
    }

    @Override
    public String getMessage() {
        return mesDes;
    }

    public double getExceeding() {
        return exceeding;
    }
}
