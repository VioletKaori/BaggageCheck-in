package com.quyon.CustomerException;

public class BaggageExceedLimitExceptiom extends RuntimeException {
    /*
    * 错误返回码retCd：
    * 1 重量超出限制
    * 2 总尺寸超出限制
    * 3 长度超出限制
    * 4 宽度超出限制
    * 5 高度超出限制
     */
    private int retCd;
    private String mesDes;
    public BaggageExceedLimitExceptiom(int _retCd,String _mesDes){
        super(_mesDes);
        assert (_retCd>0&&_retCd<6);
        retCd = _retCd;
        mesDes = _mesDes;
    }

    public String getMessage() {
        return mesDes;
    }

    public int getRetCd() {
        return retCd;
    }
}
