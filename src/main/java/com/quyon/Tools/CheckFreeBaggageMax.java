package com.quyon.Tools;

import com.quyon.CustomerException.BaggageExceedFree;
import com.quyon.CustomerException.OutBounds;
import com.quyon.Entity.*;

import java.util.ArrayList;

/*
*计算单个乘客所带行李
* 是否在免费托运范围
* 优先计算行李数量
 */
public class CheckFreeBaggageMax {
    public static ArrayList<BaggageExceedFree> check(Regional regional,//航线区域
                                  PassengerType passengerType,//乘客类型（成年，婴儿）
                                  DiscountPassengers discountPassengers, //乘客类型（普通会员，会员，特殊乘客）
                                  Cabin cabin,//舱位类型
                                  ArrayList<Baggage>baggageArrayList//行李
    ) {

        double weightLimit = 0;//单件重量限制（国内航班特殊处理）
        int numLimit = 0;//数目限制
        double sizeLimit = 158;//单件尺寸限制，默认158cm

        ArrayList<BaggageExceedFree>baggageExceedFreeArrayList = new ArrayList<>();

        switch (regional){//从枚举类型变量地区开始作为判断入口
            case AreaZero:{//国内航班，特殊计算
                if (passengerType==PassengerType.Adult){//国内航班成年人
                    switch (cabin){
                        case FirstClass:{//国内航班成年人头等舱
                            weightLimit = 40;
                            break;
                        }
                        case BussinessClass:{//国内航班成人公务舱
                           weightLimit = 30;
                           break;
                            }
                        case EconomyClass:{//经济舱
                            weightLimit = 20;
                            break;
                        }
                        case PearlEconomyClass:{
                            weightLimit = 20;
                            break;
                        }
                    }
                }else {//国内航班婴儿
                    weightLimit = 10;
                }
                double discount = 0;//额外添加重量
                switch (discountPassengers){
                    case NormalMember:{
                        discount = 0;
                        break;
                    }
                    case GoldMember:{
                        discount = 20;
                        break;
                    }
                    case SilverCardMember:{
                        discount = 10;
                        break;
                    }
                    case SpecialPassengers:{//国内航班不存在第四类特殊乘客
                        discount = 0;
                        break;
                    }
                }
                double totalWeight = 0;
                for (Baggage baggage : baggageArrayList)//计算行李总重量
                    totalWeight +=baggage.getWeight();
                if (totalWeight>=weightLimit+discount){//行李总重量超出上限
                    baggageExceedFreeArrayList.add(new BaggageExceedFree(1,"总质量超出限制",(totalWeight-weightLimit)));
                }
                return baggageExceedFreeArrayList;//国内航班计算完毕，可直接返回
            }
            //以下为外国航线，首先判断行李件数和单件重量上限
            case AreaOne:{//区域一
                switch (cabin){
                    case FirstClass:{
                        weightLimit = 32;
                        numLimit = 3;
                    }
                    case BussinessClass:{
                        weightLimit = 32;
                        numLimit = 2;
                    }
                    case PearlEconomyClass:{
                        weightLimit = 23;
                        numLimit = 2;
                    }
                    case EconomyClass:{
                        weightLimit = 23;
                        numLimit = 2;
                    }
                }
            }
            case AreaTwo:{
                switch (cabin){
                    case FirstClass:{
                        weightLimit = 32;
                        numLimit = 3;
                    }
                    case BussinessClass:{
                        weightLimit = 32;
                        numLimit = 2;
                    }
                    case PearlEconomyClass:{
                        weightLimit = 32;
                        numLimit = 1;
                    }
                    case EconomyClass:{
                        weightLimit = 32;
                        numLimit = 1;
                    }
                }
            }
            case AreaThree:{
                switch (cabin){
                    case FirstClass:{
                        weightLimit = 32;
                        numLimit = 3;
                    }
                    case BussinessClass:{
                        weightLimit = 32;
                        numLimit = 2;
                    }
                    case PearlEconomyClass:{
                        weightLimit = 23;
                        numLimit = 2;
                    }
                    case EconomyClass:{
                        weightLimit = 23;
                        numLimit = 2;
                    }
                }
            }
            case AreaFour:{
                switch (cabin){
                    case FirstClass:{
                        weightLimit = 32;
                        numLimit = 3;
                    }
                    case BussinessClass:{
                        weightLimit = 23;
                        numLimit = 3;
                    }
                    case PearlEconomyClass:{
                        weightLimit = 23;
                        numLimit = 2;
                    }
                    case EconomyClass:{
                        weightLimit = 23;
                        numLimit = 1;
                    }
                }
            }
            case AreaFive:{
                switch (cabin){
                    case FirstClass:{
                        weightLimit = 32;
                        numLimit = 3;
                    }
                    case BussinessClass:{
                        weightLimit = 32;
                        numLimit = 2;
                    }
                    case PearlEconomyClass:{
                        weightLimit = 23;
                        numLimit = 1;
                    }
                    case EconomyClass:{
                        weightLimit = 23;
                        numLimit = 1;
                    }
                }
            }
        }

        if (passengerType == PassengerType.BabyWithoutSeat){//非国内航班不占座婴儿处理，无视航班类型
            weightLimit = 10;
            numLimit = 1;
            sizeLimit = 115;
        }

        if(discountPassengers!=DiscountPassengers.NormalMember) //外国航线非普通特殊用户添加一件
            numLimit+=1;
        else
            numLimit+=0;

        if (baggageArrayList.size()>numLimit)
            baggageExceedFreeArrayList.add(new BaggageExceedFree(2,"超出免费行李件数限制",baggageArrayList.size()-numLimit));
        for (Baggage baggage:baggageArrayList){//检查每一件行李
            if (baggage.getWeight()>weightLimit)
                baggageExceedFreeArrayList.add(new BaggageExceedFree(3,"单件行李重量超出免费限制",baggage.getWeight()-weightLimit));
            else if (baggage.getSize()>sizeLimit)
                baggageExceedFreeArrayList.add(new BaggageExceedFree(4,"单件行李尺寸超出免费限制",baggage.getSize()-sizeLimit));
        }
        return baggageExceedFreeArrayList;
    }
}
