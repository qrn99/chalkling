import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;

import teamchalkling.chalkling.Main;

import java.io.IOException;


public class LoginWebAPITest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        Main.main(new String[] {});
    }


    @Test
    public void testCorrectCredentials() throws IOException, JSONException {
        // NOTE: It assumes user1 has a password of pass1 and exist in the database.
        String postUrl = "http://localhost:5000/api/login";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject loginJsonObject = new JSONObject();
        loginJsonObject.put("username", "user1");
        loginJsonObject.put("password", "pass1");
        loginJsonObject.put("isLogin", "false");

        HttpEntity<String> request =
                new HttpEntity<String>(loginJsonObject.toString(), headers);

        String resultAsJsonStr =
                restTemplate.postForObject(postUrl, request, String.class);
        JsonNode root = objectMapper.readTree(resultAsJsonStr);

        assertNotNull(resultAsJsonStr);
        assertNotNull(root);
        assertEquals("true", root.path("isLogin").asText());


//        postUrl = "http://localhost:5000/api/login";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        loginJsonObject = new JSONObject();
        loginJsonObject.put("username", "user1");
        loginJsonObject.put("password", "pass2");
        loginJsonObject.put("isLogin", "false");

        request = new HttpEntity<String>(loginJsonObject.toString(), headers);

        resultAsJsonStr = restTemplate.postForObject(postUrl, request, String.class);
        root = objectMapper.readTree(resultAsJsonStr);

        assertNotNull(resultAsJsonStr);
        assertNotNull(root);
        assertEquals("false", root.path("isLogin").asText());
    }


}
