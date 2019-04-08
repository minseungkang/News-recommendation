package weonforall.capstone.news_recommendation.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.ArticleVO;
import javax.inject.Inject;

@Repository
public class ArticleDAO implements IArticleDAO {
    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "/resources.mapper.articleMapper";

    @Override
    public void insertArticle(ArticleVO articleVO) {
        sqlSession.insert(NAMESPACE + ".insertArticle", articleVO);
    }
    @Override
    public ArticleVO getArticle(Long aid) {
        return sqlSession.selectOne(NAMESPACE + ".getArticle", aid);
    }
}
