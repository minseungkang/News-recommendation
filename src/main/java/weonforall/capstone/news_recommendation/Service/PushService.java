package weonforall.capstone.news_recommendation.Service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PushService {

    @Scheduled(cron = "0 0/5 * * * *")
    public void TestScheduler() {
        System.out.println("scheduling test");
    }
}
