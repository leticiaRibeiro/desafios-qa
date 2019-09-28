package com.idwall.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtil {

    HttpURLConnection huc = null;

    public Response testaLink(String url) {

        try {
            huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            return new Response(huc.getResponseCode(), huc.getURL().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, url);
        }
    }
}
