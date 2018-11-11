package tests;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    public String getFromFile(String filename) {
        //get from json file
        return "this is random input";
    }


    @Test
    public void storageTest() {

        String input = "this is random input";
        String filename = "filename";
        assertEquals(input, getFromFile(filename));
    }
}
