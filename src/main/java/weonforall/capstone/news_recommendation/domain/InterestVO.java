package weonforall.capstone.news_recommendation.domain;

public class InterestVO {

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;
    private String kid;

    @Override
    public String toString() {
        return "InterestVO{" +
                "uid='" + uid + '\'' +
                ", kid=" + kid +
                '}';
    }
}
