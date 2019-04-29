package weonforall.capstone.news_recommendation.persistence;

import weonforall.capstone.news_recommendation.domain.InterestVO;

import java.util.List;

public interface IInterestDAO {
    void insertInterestKeyword(InterestVO interestVO);

    List<InterestVO> getInterestKeywordList(String uid);

    InterestVO getInterestKeyword(InterestVO interestVO);
}
