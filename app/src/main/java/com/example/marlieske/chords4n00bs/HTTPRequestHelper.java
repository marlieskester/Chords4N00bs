package com.example.marlieske.chords4n00bs;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marlieske on 10-1-2017.
 */
public class HTTPRequestHelper {
    public static String executeRequest(Object Keyword, String origin){
        String result = "";
        URL link;
        String APIkey = "20bb483cdd4b3050a86e988987c416573c96080b";
        String UkuleleKey = "d41d8cd98f00b204e9800998ecf8427e";
        try {
            if (origin.equals("song")){
                link = new URL("http://api.guitarparty.com/v2/songs/?query=" + Keyword);
            }
//            else if (origin.equals("ukulele")) {
//                String parseKeyword = Keyword.toString();
//                char char2 = parseKeyword.charAt(1);
//
//                link = new URL("\"http://ukulele-chords.com/get?" + UkuleleKey + "&r=C&typ=sus2")
           // }
         else {
                link = new URL("http://api.guitarparty.com/v2/chords/?query=" + Keyword);
            }
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();

            // misschien werkt het, misschien niet, maar deze is dus voor song& artist
            connection.setRequestProperty("Guitarparty-Api-Key", APIkey);
            Integer ResponseCode = connection.getResponseCode();
            Log.d("HTTP", "Y "+link);

            if (ResponseCode >= 300 && ResponseCode <= 200) {
                // if responsecode shows error, get errorstream
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                result = String.valueOf(br);
                Log.d("HTTP", "fout");
                //return String.valueOf(br);
            } else {
                Log.d("HTTP", ResponseCode.toString());
                // else copy information to result
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = br.readLine();
                while (line != null){
                    result = result + line;
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("HTTP", "x" + result);
        return result;
    }
}
