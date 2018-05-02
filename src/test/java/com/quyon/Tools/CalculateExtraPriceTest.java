package com.quyon.Tools;
import com.quyon.Entity.*;
import com.quyon.Tools.CheckFreeBaggageMaxTest;
import org.testng.annotations.*;

import java.util.ArrayList;

public class CalculateExtraPriceTest {
    class Passenger{
        Regional regional;
        PassengerType passengerType;
        DiscountPassengers discountPassengers;
        Cabin cabin;
        ArrayList<Baggage> baggages = new ArrayList<>();
        void addBaggage(Baggage baggage){
            baggages.add(baggage);
        }
        int economyClassPrice;
    }
    private double price = 0;
    @DataProvider(name = "test1")
    public Object[][]getPassenger(){
        Passenger passenger = new Passenger();
        passenger.regional =  Regional.AreaFour;
        passenger.passengerType = PassengerType.Adult;
        passenger.discountPassengers = DiscountPassengers.GoldMember;
        passenger.cabin = Cabin.FirstClass;
        passenger.addBaggage(new Baggage(20,10,30,10));
        passenger.addBaggage((new Baggage(20,50,39,20)));
        return new Object[][]{{passenger}};
//        return new CheckFreeBaggageMaxTest().getPassenger();
    }
    @AfterMethod
    public void tearDown(){
        System.out.println("额外价格："+price);
    }
    @Test(dataProvider = "test1")
    public void testCheck(Passenger passenger){
        price = CalculateExtraPrice.getExtraPrice(
                passenger.regional,
                passenger.passengerType,
                passenger.discountPassengers,
                passenger.cabin,
                passenger.baggages,
                1000);
    }

}
