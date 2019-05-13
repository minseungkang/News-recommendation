package weonforall.capstone.news_recommendation.persistence;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.ArticleHistoryVO;
import weonforall.capstone.news_recommendation.domain.ArticleVO;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleHistoryDAO implements IArticleHistoryDAO {
    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "/resources.mapper.articleHistoryMapper";

    @Override
    public void insertArticleHistory(ArticleHistoryVO articleHistoryVO) {
        sqlSession.insert(NAMESPACE + ".insertArticleHistory", articleHistoryVO);
    }

    @Override
    public void updateArticleHistory(ArticleHistoryVO articleHistoryVO) {
        sqlSession.update(NAMESPACE + ".updateArticleHistory", articleHistoryVO);
    }

    @Override
    public List<ArticleVO> getRecommendedArticles(String uid) {
        return sqlSession.selectList(NAMESPACE + ".getRecommendedArticles", uid);
    }

    @Override
    public List<ArticleVO> getRecommendedArticlesByDate(String uid, Timestamp timestamp) {

        Timestamp endTimestamp = new Timestamp(timestamp.getTime() + 86400 * 1000);
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("startTimestamp", timestamp);
        params.put("endTimestamp", endTimestamp);
        return sqlSession.selectList(NAMESPACE + ".getRecommendedArticlesByDate", params);
    }

    @Override
    public ArticleHistoryVO getArticleHistory(Long aid, String uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("aid", aid);
        params.put("uid", uid);

        return sqlSession.selectOne(NAMESPACE + ".getArticleHistory", params);
    }

}
