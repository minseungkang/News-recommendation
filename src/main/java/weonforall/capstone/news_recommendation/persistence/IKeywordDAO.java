package weonforall.capstone.news_recommendation.persistence;

import weonforall.capstone.news_recommendation.domain.KeywordVO;

import java.util.List;

public interface IKeywordDAO {
    KeywordVO getKeywordVO(String kid);
    KeywordVO getkeywordVOByKeyword(String keyword);
    List<String> getKeywords();
}
