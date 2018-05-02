package com.quyon.Tools;

import com.quyon.CustomerException.BaggageExceedFree;
import com.quyon.Entity.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class CheckFreeBaggageMaxTest {

    private Regional regional;
    private PassengerType passengerType;
    private DiscountPassengers discountPassengers;
    private Cabin cabin;
    private ArrayList<Baggage> baggages = new ArrayList<>();
    private ArrayList<BaggageExceedFree> baggageExceedFreeArrayList;

    @BeforeMethod
    public void setUp() {
        regional = Regional.AreaFour;
        passengerType = PassengerType.Adult;
        discountPassengers = DiscountPassengers.GoldMember;
        cabin = Cabin.FirstClass;
        baggages.add(new Baggage(100000,10,30,10));
        baggages.add(new Baggage(20,39,23,12));
        baggages.add(new Baggage(20,39,23,12));
    }

    @AfterMethod
    public void tearDown() {
        if (baggageExceedFreeArrayList.size()==0){
            System.out.print("该乘客的行李可以免费托运");
        }else {
            for (BaggageExceedFree e:baggageExceedFreeArrayList){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testCheck() {
        baggageExceedFreeArrayList = CheckFreeBaggageMax.check(regional,passengerType,discountPassengers,cabin,baggages);
    }
}