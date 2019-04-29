package weonforall.capstone.news_recommendation.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.KeywordVO;

import javax.inject.Inject;
import java.util.List;

@Repository
public class KeywordDAO implements IKeywordDAO{

    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "/resources.mapper.keywordMapper";


    @Override
    public KeywordVO getKeywordVO(String kid) {
        return sqlSession.selectOne(NAMESPACE + ".getKeywordVO", kid);
    }

    @Override
    public KeywordVO getkeywordVOByKeyword(String keyword) {
        return sqlSession.selectOne(NAMESPACE + ".getKeywordVOByKeyword", keyword);
    }

    @Override
    public List<String> getKeywords() {
        return sqlSession.selectList(NAMESPACE + ".getKeywords");
    }
}
