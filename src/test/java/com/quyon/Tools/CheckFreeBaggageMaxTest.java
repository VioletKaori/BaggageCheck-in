package com.quyon.Tools;

import com.quyon.CustomerException.BaggageExceedFree;
import com.quyon.Entity.*;
import org.testng.annotations.*;
import java.util.ArrayList;


public class CheckFreeBaggageMaxTest {

    private ArrayList<BaggageExceedFree> baggageExceedFreeArrayList;

    class Passenger{
        Regional regional;
        PassengerType passengerType;
        DiscountPassengers discountPassengers;
        Cabin cabin;
        ArrayList<Baggage> baggages = new ArrayList<>();
        void addBaggage(Baggage baggage){
            baggages.add(baggage);
        }
    }

    @DataProvider(name = "test1")
    public Object[][]getPassenger(){
        Passenger passenger = new Passenger();
        passenger.regional =  Regional.AreaFour;
        passenger.passengerType = PassengerType.Adult;
        passenger.discountPassengers = DiscountPassengers.GoldMember;
        passenger.cabin = Cabin.FirstClass;
        passenger.addBaggage(new Baggage(20,10,30,10));
        passenger.addBaggage((new Baggage(20,50,39,20)));
        //passenger.addBaggage(new Baggage(10,180,30,10));
        return new Object[][]{{passenger}};
    }



    @AfterMethod
    public void tearDown() {
        if (baggageExceedFreeArrayList.size()==0){
            System.out.println("该乘客的行李可以免费托运");
        }else {
            for (BaggageExceedFree e:baggageExceedFreeArrayList){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test(dataProvider = "test1")
    public void testCheck(Passenger passenger) {
        baggageExceedFreeArrayList = CheckFreeBaggageMax.check(
                passenger.regional,
                passenger.passengerType,
                passenger.discountPassengers,
                passenger.cabin,
                passenger.baggages);
    }
}