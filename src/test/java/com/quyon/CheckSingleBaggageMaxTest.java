package com.quyon;

import com.quyon.Entity.Baggage;
import com.quyon.Entity.Route;
import com.quyon.Tools.CheckSingleBaggageMax;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckSingleBaggageMaxTest {

    private Route route;
    private Baggage baggage;
    @BeforeEach
    void setUp() {
        route = new Route(false,true,true);
        baggage = new Baggage(500,01,50,50);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void check() {
        try {
            CheckSingleBaggageMax.check(route,baggage);
        }
        catch (Exception e){
            //e.printStackTrace();
            e.printStackTrace(System.out);
        }
    }
}