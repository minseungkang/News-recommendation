package weonforall.capstone.news_recommendation.Service;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import javax.inject.Inject;
import java.net.URI;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PushService {

    private final String FIREBASE_URL = "http://fcm.googleapis.com/fcm/send";
    @Inject
    private IUserDAO userDAO;


    @Scheduled(cron = "0 0/1 * * * *")
    public void Scheduler() {
        long timeLong = System.currentTimeMillis();

        timeLong = timeLong / 10000 * 10000;
        Time curTime = new Time(timeLong);

        System.out.println(curTime);

        List<String> tokenList = userDAO.getPushTokens(curTime);

        for (String token : tokenList) {
            //TODO call push API

            String body = "{\n" +
                            "\"notification\" : {\n" +
                                "\"body\":" +
                                    "\"test\"" + ",\n" +
                                "\"title\" : " +
                                    "\"TEST\"" + "\n" +
                            "},\n" +
                            "\"to\" : " + "\"" + token + "\"\n" +
                        "}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "key=AIzaSyCR7n0WcvZiCLjAN81yaAgdelaBWwPuqy0");

            HttpEntity param = new HttpEntity(body, headers);

            /*
            LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("body", "message");
            map.add("title", "test");
            map.add("to", token);
            */

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.postForObject(FIREBASE_URL, param, String.class);
            System.out.println(result);
            //ResponseEntity<String> responseEntity = restTemplate.postForEntity( map, String.class);
            //System.out.println(responseEntity);
        }
    }

}
