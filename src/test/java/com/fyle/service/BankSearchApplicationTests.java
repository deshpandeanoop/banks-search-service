package com.fyle.service;

import com.fyle.service.bean.JwtToken;
import com.fyle.service.request.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankSearchApplicationTests {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void Given_ValidCredentials_AuthenticateAPI_Should_Return_Ok_Response() throws Exception{
        ResponseEntity<JwtToken> response = restTemplate.postForEntity(createAbsoluteUri("/authenticate"),
                new AuthenticationRequest("fyle", "fyle"), JwtToken.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void Given_InValidCredentials_AuthenticateAPI_Should_Return_4x_Response(){
        ResponseEntity<String> response = restTemplate.postForEntity(createAbsoluteUri("/authenticate"),
                new AuthenticationRequest("invalid_user_name","invalid_password"), String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void Given_NoJwtToken_BanksDetailsApi_Should_Return_4x_Response(){
        ResponseEntity<String> response = restTemplate.getForEntity(createAbsoluteUri("/banks/ABHY0065001"), String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void Given_Valid_Jwt_Header_BankDetailsApi_Should_Return_Ok_Response(){
        ResponseEntity<String> response = restTemplate.exchange(createAbsoluteUri("/banks/ABHY0065001"), HttpMethod.GET, constructHttpEntityWithAuthorizationHeaders(), String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void Given_NoJwtToken_BranchDetailsApi_Should_Return_4x_Response(){
        ResponseEntity<String> response = restTemplate.getForEntity(createAbsoluteUri("/banks/ABHYUDAYA COOPERATIVE BANK LIMITED/branches/MUMBAI?offset=12&limit=50"), String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void Given_Valid_Jwt_Header_BranchDetailsApi_Should_Return_Ok_Response(){
        ResponseEntity<String> response = restTemplate.exchange(createAbsoluteUri("/banks/ABHYUDAYA COOPERATIVE BANK LIMITED/branches/MUMBAI?offset=12&limit=50"), HttpMethod.GET, constructHttpEntityWithAuthorizationHeaders(), String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    private HttpEntity constructHttpEntityWithAuthorizationHeaders(){
        ResponseEntity<JwtToken> response = restTemplate.postForEntity(createAbsoluteUri("/authenticate"),
                new AuthenticationRequest("fyle", "fyle"), JwtToken.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer "+response.getBody().getValue());
        return new HttpEntity(httpHeaders);
    }

    private String createAbsoluteUri(String uri){
        return new StringBuilder()
                .append("http://localhost:")
                .append(port)
                .append("/")
                .append(uri).toString();
    }
}
