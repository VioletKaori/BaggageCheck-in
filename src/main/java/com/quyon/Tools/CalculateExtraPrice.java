package com.quyon.Tools;

import com.quyon.CustomerException.BaggageExceedFree;
import com.quyon.Entity.*;

import java.util.ArrayList;

/**
 * 计算在限额范围内的超出免费部分的价格
 */
public class CalculateExtraPrice {
    public static double getExtraPrice(Regional regional,
                                       PassengerType passengerType,
                                       DiscountPassengers discountPassengers,
                                       Cabin cabin,
                                       ArrayList<Baggage>baggageArrayList,
                                       double economyClassPrice){
        ArrayList<BaggageExceedFree> arrayList = CheckFreeBaggageMax.check(regional,passengerType,discountPassengers,cabin,baggageArrayList);
        double extraPrice = 0;
        for (BaggageExceedFree e : arrayList) {
            switch (e.getTypeCode()) {
                case 1: {//总重量超出限制（国内航班），逾重行李费率为每公斤按逾重行李票填开当日所适用的经济舱普通票价的 1.5% 计算
                    assert (arrayList.size() == 1);
                    extraPrice = economyClassPrice * 0.015 * arrayList.get(0).getExceeding();
                }
                case 2: {//行李件数超出限制
                    if (regional == Regional.AreaOne || regional == Regional.AreaThree) {
                        extraPrice += 1000 + (e.getExceeding() - 1) * 2000;//区域一和三，不论舱位类型，超件收费标准一致：超件第一件1000，第二件以及更多2000/件
                    } else if (regional == Regional.AreaTwo || regional == Regional.AreaFour) {
                        extraPrice += 450 + (e.getExceeding() - 1) * 1300;//区域二和四，所有舱位一致：超件第一件450，第二件以及更多1300/件
                    }
                    break;
                }
                case 3: {//单件行李重量超出限制
                    if (regional == Regional.AreaOne) {//区域一
                        //assert (e.getExceeding()<45);//检查是否超出托运限制
                        if (cabin == Cabin.FirstClass || cabin == Cabin.BussinessClass) {//头等舱和商务舱
                            extraPrice = +3000;
                        } else if (cabin == Cabin.EconomyClass || cabin == Cabin.PearlEconomyClass) {//经济舱和明珠经济舱
                            if (e.getExceeding() <= 32)
                                extraPrice += 1000;
                            else if (e.getExceeding() > 32 && e.getExceeding() <= 45)
                                extraPrice += 3000;
                        }
                    } else if (regional == Regional.AreaTwo) {//区域二
                        extraPrice += 3000;
                    } else if (regional == Regional.AreaThree) {//区域三
                        if (cabin == Cabin.FirstClass || cabin == Cabin.BussinessClass) {//头等舱和商务舱
                            extraPrice += 3000;
                        } else if (cabin == Cabin.EconomyClass || cabin == Cabin.PearlEconomyClass) {//经济舱和明珠经济舱
                            if (e.getExceeding() <= 32)
                                extraPrice += 2000;
                            else if (e.getExceeding() > 32 && e.getExceeding() <= 45)
                                extraPrice += 3000;
                        }
                    }else if (regional == Regional.AreaFour){//区域四
                        if (cabin ==Cabin.FirstClass){//头等舱
                            extraPrice +=3000;
                        }else {
                            if (e.getExceeding() <= 32)
                                extraPrice += 1000;
                            else if (e.getExceeding() > 32 && e.getExceeding() <= 45)
                                extraPrice += 3000;
                        }
                    }
                }
                case 4:{//单件行李尺寸超出限制
                    extraPrice+=1000;//所有区域，所有舱位，超出尺寸价格一致：1000（/件？）
                }
            }
        }
        return extraPrice;
    }
}
