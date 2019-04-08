package weonforall.capstone.news_recommendation.domain;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class ArticleVO {

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getUploadedTimestamp() {
        return uploadedTimestamp;
    }

    public void setUploadedTimestamp(Timestamp uploadedTimestamp) {
        this.uploadedTimestamp = uploadedTimestamp;
    }

    @JsonProperty("aid")
    private long aid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("contentPath")
    private String contentPath;
    @JsonProperty("url")
    private String url;
    @JsonProperty("score")
    private double score;
    @JsonProperty("timestamp")
    private Timestamp timestamp;
    @JsonProperty("uploadedTimestamp")
    private Timestamp uploadedTimestamp;

    @Override
    public String toString() {
        return "ArticleVO{" +
                "aid='" + aid + "\'\n" +
                ", title='" + title + "\'\n" +
                ", contentPath='" + contentPath + "\'\n" +
                ", url='" + url + "\'\n" +
                ", score='" + score + "\'\n" +
                ", timestamp=`" + timestamp + "\'\n" +
                ", uploadedTimestamp='" +uploadedTimestamp + "\'\n" +
                '}';
    }


}