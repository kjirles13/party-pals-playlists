package com.techelevator.dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.SpotifyToken;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SpotifyAPIDao implements SpotifyDao{
    private String clientSecret = "5dfba35f85ef4c2aae0b83bfd2d1766f";
    private String clientId = "ff7f710ce2564ce19c72faebda1dfa0e";

    @Override
    public SpotifyToken getToken() {
        HttpClient httpClient = HttpClient.newHttpClient();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "client_credentials");

        String form = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " +
                        Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> response = null;
        SpotifyToken spotifyToken = new SpotifyToken();

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            spotifyToken = objectMapper.readValue(response.body(), SpotifyToken.class);
        } catch (RuntimeException | InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return spotifyToken;
    }
}
