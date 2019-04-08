package weonforall.capstone.news_recommendation.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.UserVO;

import javax.inject.Inject;

@Repository
public class UserDAO implements IUserDAO {
    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "/resources.mapper.userMapper";

    public void insertUser(UserVO userVO) {
        sqlSession.insert(NAMESPACE + ".insertUser", userVO);
    }

    public UserVO getUser(String uid) {
        return sqlSession.selectOne(NAMESPACE + ".getUser", uid);
    }
}
