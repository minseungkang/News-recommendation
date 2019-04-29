package weonforall.capstone.news_recommendation.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.InterestVO;

import javax.inject.Inject;
import java.util.List;

@Repository
public class InterestDAO implements IInterestDAO {
    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "/resources.mapper.interestMapper";

    @Override
    public void insertInterestKeyword(InterestVO interestVO) {
        sqlSession.insert(NAMESPACE + ".insertInterestKeyword", interestVO);
    }

    @Override
    public List<InterestVO> getInterestKeywordList(String uid) {
        return sqlSession.selectList(NAMESPACE + ".getInterestKeywordList", uid);
    }

    @Override
    public InterestVO getInterestKeyword(InterestVO interestVO) {
        return sqlSession.selectOne(NAMESPACE + ".getInterestKeyword", interestVO);
    }

}
