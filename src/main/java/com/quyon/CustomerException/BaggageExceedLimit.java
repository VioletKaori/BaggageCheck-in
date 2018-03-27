package com.quyon.CustomerException;

public class BaggageExceedLimit implements OutBounds {
    /*
    * 单件行李超出上限
    * 错误返回码retCd：
    * 1 重量超出限制
    * 2 总尺寸超出限制
    * 3 长度超出限制
    * 4 宽度超出限制
    * 5 高度超出限制
     */
    private int retCd;
    private String mesDes;

    public BaggageExceedLimit(int _retCd, String _mesDes){
        assert (_retCd>0&&_retCd<6);
        retCd = _retCd;
        mesDes = _mesDes;
    }

    @Override
    public int getTypeCode() {
        return retCd;
    }

    @Override
    public String getMessage() {
        return mesDes;
    }

}
