package weonforall.capstone.news_recommendation.Service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestClientException;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import javax.inject.Inject;
import java.sql.Time;
import java.util.List;

@Service
public class SchedulerService {

    private final Integer ONE_MINUTE = 60000;

    private final String FIREBASE_URL = "http://fcm.googleapis.com/fcm/send";
    private final String AUTHOR_KEY   = "key=AIzaSyCR7n0WcvZiCLjAN81yaAgdelaBWwPuqy0";
    private final String PUSH_TITLE   = "News recommender";
    private final String PUSH_BODY    = "News Updated.";
    private final String contents = "{\"notification\":{\"body\":" +
            "\"" + PUSH_BODY + "\",\n" +
            "\"title\" : " +
            "\"" + PUSH_TITLE + "\"\n" +
            "},\n";

    private final String RECOMMEND_SERVER_URL = "http://13.209.79.247:5555/suggest/";
    private final int TIMEZONE_VALUE = ONE_MINUTE * 60 * 9;
    @Inject
    private IUserDAO userDAO;

    @Scheduled(cron = "0 2/5 * * * *")
    public void requestScheduler() {
        System.out.println("requestScheduler");
        long timeLong = System.currentTimeMillis() + TIMEZONE_VALUE;
        timeLong = timeLong / 10000 * 10000 + (ONE_MINUTE * 3);
        Time curTime = new Time(timeLong);
        System.out.println(curTime);

        AsyncRestTemplate restTemplate = new AsyncRestTemplate();

        // define HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<String> uidList = userDAO.getUidListByPushTime(curTime);

        HttpEntity<String> param = new HttpEntity<>(headers);
        for (String uid : uidList) {
            System.out.println("recommend_request/" + uid);
            try {
                restTemplate.postForEntity(RECOMMEND_SERVER_URL + uid, param, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void pushScheduler() {
        System.out.println("pushScheduler");
        long timeLong = System.currentTimeMillis() + TIMEZONE_VALUE;
        timeLong = timeLong / 10000 * 10000;
        Time curTime = new Time(timeLong);
        System.out.println(curTime);

        // request to Firbase URL
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();

        // define HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AUTHOR_KEY);

        // search tokens from DB
        List<String> tokenList = userDAO.getPushTokens(curTime);

        // send push notification to each client.
        for (String token : tokenList) {
            System.out.println("push_noti_request/" + token);
            String body = contents + "\"to\" : " + "\"" + token + "\"\n}";

            HttpEntity<String> param = new HttpEntity<>(body, headers);

            restTemplate.postForEntity(FIREBASE_URL, param, String.class);
        }
    }

}
