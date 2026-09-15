package com.kairav.hotelapp.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import java.net.http.*;

public class ApiClient {
    public static final String BASE_URL ="http://localhost:8080/api";
    private static final HttpClient client =HttpClient.newHttpClient();
    public static final ObjectMapper mapper =new ObjectMapper().registerModule(new JavaTimeModule());
    public static <T>T get(String path,TypeReference <T>type) throws Exception {
        HttpRequest req =HttpRequest.newBuilder().uri(URI.create(BASE_URL +path)).GET().build();
        HttpResponse <String>res =client.send(req,HttpResponse.BodyHandlers.ofString());
        if (res.statusCode()>=400) throw new RuntimeException(res.body());
        return mapper.readValue(res.body(),type);
    }
    public static <T>T post(String path,Object body,Class <T>type) throws Exception {
        String json =mapper.writeValueAsString(body);
        HttpRequest req =HttpRequest.newBuilder().uri(URI.create(BASE_URL +path)).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse <String>res =client.send(req,HttpResponse.BodyHandlers.ofString());
        if (res.statusCode()>=400) throw new RuntimeException(res.body());
        return mapper.readValue(res.body(),type);
    }
    public static <T>T put(String path,Object body,Class <T>type) throws Exception {
        String json =body ==null ?"":mapper.writeValueAsString(body);
        HttpRequest req =HttpRequest.newBuilder().uri(URI.create(BASE_URL +path)).header("Content-Type","application/json").PUT(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse <String>res =client.send(req,HttpResponse.BodyHandlers.ofString());
        if (res.statusCode()>=400) throw new RuntimeException(res.body());
        return mapper.readValue(res.body(),type);
    }
    public static void delete(String path) throws Exception {
        HttpRequest req =HttpRequest.newBuilder().uri(URI.create(BASE_URL +path)).DELETE().build();
        HttpResponse <String>res =client.send(req,HttpResponse.BodyHandlers.ofString());
        if (res.statusCode()>=400) throw new RuntimeException(res.body());
    }
}
