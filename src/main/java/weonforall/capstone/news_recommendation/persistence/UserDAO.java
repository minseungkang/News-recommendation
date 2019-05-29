package weonforall.capstone.news_recommendation.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import weonforall.capstone.news_recommendation.domain.UserVO;

import javax.inject.Inject;
import java.sql.Time;
import java.util.List;

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

    @Override
    public int checkUUid(String uid) {
        return sqlSession.selectOne(NAMESPACE + ".checkUUid", uid);
    }

    @Override
    public void updateUser(UserVO userVO) {
        sqlSession.update(NAMESPACE + ".updateUser", userVO);
    }

    @Override
    public List<String> getPushTokens(Time time) {
        return sqlSession.selectList(NAMESPACE + ".getPushTokens", time);
    }

    @Override
    public List<String> getUidListByPushTime(Time time) {
        return sqlSession.selectList(NAMESPACE + ".getUidListByPushTime", time);
    }
}
