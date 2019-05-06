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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String uid;
    private Time pushTime;
    private String token;

    @Override
    public String toString() {
        return "UserVO{" +
                "uid='" + uid + '\'' +
                ", pushTime=" + pushTime +
                ", token=" + token +
                '}';
    }

}
