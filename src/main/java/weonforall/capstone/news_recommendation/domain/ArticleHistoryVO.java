package weonforall.capstone.news_recommendation.domain;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class ArticleHistoryVO {

    //getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public Boolean getFeedback() {
        return feedback;
    }

    public void setFeedback(Boolean feedback) {
        this.feedback = feedback;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @JsonProperty("uid")
    private String uid;
    @JsonProperty("aid")
    private long aid;

    // nullable
    @JsonProperty("feedback")
    private Boolean feedback;
    @JsonProperty("timestamp")
    private Timestamp timestamp;
    // cannot be null
    @JsonProperty("read")
    private boolean read;

    public ArticleHistoryVO(String uid, Long aid, Boolean feedback) {
        this.uid = uid;
        this.aid = aid;
        this.feedback = feedback;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.read = false;
    }

    public ArticleHistoryVO() {
    }

    @Override
    public String toString() {
        return "ArticleHistoryVO{" +
                "aid='" + aid + "\'\n" +
                ", uid='" + uid + "\'\n" +
                ", feedback='" + feedback + "\'\n" +
                ", timestamp='" + timestamp + "\'\n" +
                ", read='" + read + "\'\n" +
                '}';
    }
}
