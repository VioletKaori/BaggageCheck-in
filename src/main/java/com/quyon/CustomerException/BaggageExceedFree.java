package com.quyon.CustomerException;

public class BaggageExceedFree implements OutBounds {
    public BaggageExceedFree(int _retCd, String _mesDes){
        typeCode = _retCd;
        mesDes = _mesDes;
    }
    private String mesDes;
    private int typeCode;

    @Override
    public int getTypeCode() {
        return typeCode;
    }

    @Override
    public String getMessage() {
        return mesDes;
    }
}
