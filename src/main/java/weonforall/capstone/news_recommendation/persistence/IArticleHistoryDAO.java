package weonforall.capstone.news_recommendation.persistence;

import weonforall.capstone.news_recommendation.domain.ArticleHistoryVO;
import weonforall.capstone.news_recommendation.domain.ArticleVO;

import java.util.List;

public interface IArticleHistoryDAO {
    void insertArticleHistory(ArticleHistoryVO articleHistoryVO);

    ArticleHistoryVO getArticleHistory(Long aid, String uid);

    void updateArticleHistory(ArticleHistoryVO articleHistoryVO);
    List<ArticleVO> getRecommendedArticles(String uid);

}
