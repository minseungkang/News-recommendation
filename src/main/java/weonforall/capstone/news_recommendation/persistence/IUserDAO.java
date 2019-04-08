package weonforall.capstone.news_recommendation.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import weonforall.capstone.news_recommendation.domain.UserVO;


public interface IUserDAO {
    void insertUser(UserVO userVO);

    UserVO getUser(String uid);

}
