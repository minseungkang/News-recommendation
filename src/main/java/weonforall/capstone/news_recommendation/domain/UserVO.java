package weonforall.capstone.news_recommendation.domain;

import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository
public class UserVO {

    public String getUid() {
        return uid;
    }

    public Time getPushTime() {
        return pushTime;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPushTime(Time pushTime) {
        this.pushTime = pushTime;
    }

    private String uid;
    private Time pushTime;

    @Override
    public String toString() {
        return "UserVO{" +
                "uid='" + uid + '\'' +
                ", pushTime=" + pushTime +
                '}';
    }
}
