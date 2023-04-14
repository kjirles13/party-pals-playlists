//package com.techelevator.dao;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//
//public class SpotifyAPIDao implements SpotifyDao{
//    private String clientSecret = "5dfba35f85ef4c2aae0b83bfd2d1766f";
//    private String clientId = "ff7f710ce2564ce19c72faebda1dfa0e";
//
//    @Override
//    public String getToken() {
//        String token = "";
//        try {
//            URL url = new URL("https://accounts.spotify.com/api/token");
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setDoInput(true);
//
//            String json = "{'grant_type': 'client_credentials', 'client_id': 'ff7f710ce2564ce19c72faebda1dfa0e', 'client_secret': '5dfba35f85ef4c2aae0b83bfd2d1766f'}";
//
//            try(OutputStream os = connection.getOutputStream()) {
//                byte[] input = json.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            connection.connect();
//
//            int responseCode = connection.getResponseCode();;
//
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//                try(BufferedReader br = new BufferedReader(
//                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                    StringBuilder response = new StringBuilder();
//                    String responseLine = null;
//                    while ((responseLine = br.readLine()) != null) {
//                        response.append(responseLine.trim());
//                    }
//                    System.out.println(response.toString());
//                }
//            }
//
//        } catch (IOException e) {
//
//        }
//        return token;
//    }
//}
