package com.example.cs246team22.printingapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AddSpoolActivityTest {
    // fields used together with @Parameter must be public

    @Test
    public void testSpool() {

        AddSpoolActivity tester = new AddSpoolActivity();
        assertSame("Result", "Ama", addSpool());
    }


        public String addSpool() {

            String manufacturer = "Ama";
            return manufacturer;
        }
    }
