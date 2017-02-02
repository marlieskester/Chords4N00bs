package com.example.marlieske.chords4n00bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marlieske on 10-1-2017.
 * Class creates URL's using API key & keyword, opens connection and returns JSONcontent
 */

//TODO handle Ukulele dinges
class HTTPRequestHelper {

    /****/
    private static URL createURL(Object Keyword, String origin){
        URL link = null;
        try {
            if (origin.equals("song")){
                link = new URL("http://api.guitarparty.com/v2/songs/?query=" + Keyword);
            } else {
                link = new URL("http://api.guitarparty.com/v2/chords/?query=" + Keyword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return link;
    }

    static String openConnection(Object Keyword, String Origin){
        String APIkey = "20bb483cdd4b3050a86e988987c416573c96080b";
        String result = "";
        URL link = createURL(Keyword, Origin);
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) link.openConnection();
            connection.setRequestProperty("Guitarparty-Api-Key", APIkey);
            Integer ResponseCode = connection.getResponseCode();

            if (ResponseCode >= 300 && ResponseCode <= 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                result = String.valueOf(br);
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = br.readLine();
                while (line != null) {
                    result = result + line;
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
