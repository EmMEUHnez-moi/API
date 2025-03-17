package com.willimath.api.service;

import com.willimath.api.model.UserToSave;
import org.json.JSONArray;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminAuthentificationService {

    private String token;

    private void authenticate() {
        String url = "http://localhost:8090/realms/EmMEUHnezmoi/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = Map.of(
                        "username", "bob.martin@example.com",
                        "password", "securepass",
                        "grant_type", "password",
                        "client_id", "admin-cli"
                ).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));


        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        token = (String) response.getBody().get("access_token");
    }

    public UUID createUser(UserToSave user) {
        authenticate();
        String url = "http://localhost:8090/admin/realms/EmMEUHnezmoi/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String requestBody = "{\n" +
                "    \"email\": \"" + user.email() + "\",\n" +
                "    \"enabled\": true,\n" +
                "    \"firstName\": \"" + user.name() + "\",\n" +
                "    \"lastName\": \"" + user.surname() + "\",\n" +
                "    \"credentials\": [{\n" +
                "        \"type\": \"password\",\n" +
                "        \"value\": \"" + user.password() + "\"\n" +
                "    }]\n" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        UUID userId = findUser(user);
        addRole(userId);
        return userId;
    }

    private UUID findUser(UserToSave user) {
        authenticate();
        String url = "http://localhost:8090/admin/realms/EmMEUHnezmoi/users?email=" + user.email();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        return UUID.fromString((String) jsonArray.getJSONObject(0).get("id"));
    }

    public void deleteUser(UUID userId) {
        authenticate();
        String url = "http://localhost:8090/admin/realms/EmMEUHnezmoi/users/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
    }

    private void addRole(UUID userId) {
        authenticate();
        String url = "http://localhost:8090/admin/realms/EmMEUHnezmoi/users/"+ userId + "/role-mappings/clients/f8bf6a7b-3edd-4f37-a74d-abae99663866";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String requestBody = "[\n" +
                "    {\n" +
                "        \"id\": \"12f45815-0201-433f-9502-08c150e29ea5\",\n" +
                "        \"name\": \"User\"\n" +
                "    }\n" +
                "]";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, request, String.class);
    }
}