package com.quyon.Tools;

import com.quyon.CustomerException.BaggageExceedLimit;
import com.quyon.CustomerException.OutBounds;
import com.quyon.Entity.Baggage;
import com.quyon.Entity.Route;

/*
 * 计算单件行李是否符合上限要求
 * 不符合则抛出异常
 */
public final class CheckSingleBaggageMax {
    public static OutBounds check(Route route, Baggage baggage) {
        if (route.isDomestic()){//国内航班
            if(baggage.getWeight()>50)
                return new BaggageExceedLimit(1,"该件行李重量超出限制"+(baggage.getSize()-50)+"kg");
            if(baggage.getLength()>60)
                return new BaggageExceedLimit(3,"该件行李长度超出限制"+(baggage.getLength()-60)+"cm");
            if(baggage.getWidth()>40)
                return new BaggageExceedLimit(4,"该件行李宽度超出限制"+(baggage.getWidth()-40)+"cm");
            if(baggage.getHeight()>100)
                return new BaggageExceedLimit(5,"该件行李高度超出限制"+(baggage.getHeight()-100)+"cm");
        }

        if (route.isInternational()){//国际航班
            if (baggage.getSize() > 158)
                return new BaggageExceedLimit(2,"该件行李总尺寸超出限制"+(baggage.getSize()-158)+"cm");
            if(route.isArriveUSA()) {//到达美国
                if (baggage.getWeight() > 45)
                    return new BaggageExceedLimit(1, "该件行李重量超出限制" + (baggage.getWeight()-45)+"kg");
            }else {
                if (baggage.getWeight() > 32)
                    return new BaggageExceedLimit(1,"该件行李重量超出限制"+(baggage.getWeight()-32)+"kg");
            }
        }
        return null;
    }
}

