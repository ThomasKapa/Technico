package com.technicoCompany.technico;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OwnerControllerTest {


    @Test
    public void getAllOwners() throws Exception {

        URL url = new URL("http://localhost:8080/technico/users");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        assertEquals(200, responsecode);


    }

}