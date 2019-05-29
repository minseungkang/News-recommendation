package weonforall.capstone.news_recommendation.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import weonforall.capstone.news_recommendation.domain.UserVO;

import java.sql.Time;
import java.util.List;


public interface IUserDAO {
    void insertUser(UserVO userVO);

    UserVO getUser(String uid);

    int checkUUid(String uid);

    void updateUser(UserVO userVO);

    List<String> getPushTokens(Time time);

    List<String> getUidListByPushTime(Time time);
}
