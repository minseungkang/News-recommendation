package weonforall.capstone.news_recommendation.persistence;


import weonforall.capstone.news_recommendation.domain.ArticleVO;

public interface IArticleDAO {
    void insertArticle(ArticleVO articleVO);

    ArticleVO getArticle(Long aid);
}
