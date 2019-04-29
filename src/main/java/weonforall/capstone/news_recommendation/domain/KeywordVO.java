package weonforall.capstone.news_recommendation.domain;

import org.springframework.stereotype.Repository;

@Repository
public class KeywordVO {

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String keyword;
    private String kid;

    @Override
    public String toString() {
        return "KeywordVO{" +
                "kid='" + kid + '\'' +
                ", keyword=" + keyword +
                '}';
    }


}
