package com.company;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
// import jdk.incubator.http.*;

public class Main {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        System.out.println(httpClient.version());
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://www.google.de")).GET().build();
            Map<String, List<String>> headers = httpRequest.headers().map();
            headers.forEach((k, v) -> System.out.println(k + "-" + v));
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandler.asString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
