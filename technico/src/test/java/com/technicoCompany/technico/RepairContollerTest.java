package com.technicoCompany.technico;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairContollerTest {


    @Test
    public void getAllRepairs() throws Exception {

        URL url = new URL("http://localhost:4200/technico/repairs");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        assertEquals(200, responsecode);


    }
}