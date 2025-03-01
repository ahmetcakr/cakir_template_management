package com.cakir.templateManagement.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HttpService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Map<String, Object> sendGetRequest(String url, String apiKey, String authorizationType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationType + " " + apiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return parseJson(response.getBody());
    }

    public Map<String, Object> sendPostRequest(String url, String jsonBody, String apiKey, String authorizationType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationType + " " + apiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return parseJson(response.getBody());
    }

    private Map<String, Object> parseJson(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON parse edilemedi: " + json, e);
        }
    }
}
