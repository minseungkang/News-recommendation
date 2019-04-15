package weonforall.capstone.news_recommendation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.enums.Status;
import weonforall.capstone.news_recommendation.model.Result;
import weonforall.capstone.news_recommendation.persistence.IArticleDAO;
import weonforall.capstone.news_recommendation.persistence.IArticleHistoryDAO;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import javax.inject.Inject;
import java.sql.Time;
import java.util.Map;
import java.util.UUID;

@RestController
public class User {
    @Inject
    IArticleHistoryDAO iArticleHistoryDAO;

    @Inject
    IArticleDAO iArticleDAO;

    @Inject
    IUserDAO iUserDAO;

    @RequestMapping(value = "/user/initUser", method = RequestMethod.POST)
    public @ResponseBody Result initUser(Map<String, Object> param) {

        Result result = null;

        try {
            System.out.println("/user/initUser");
            String uid = UUID.randomUUID().toString();

            int maximumLoopCnt = 100;
            int loopCnt = 0;
            while (loopCnt < maximumLoopCnt) {
                uid = UUID.randomUUID().toString();

                // check uid exists in db
                if (iUserDAO.checkUUid(uid) == 0) break;
                loopCnt++;
            }

            if (loopCnt >= maximumLoopCnt) {
                result = new Result(Status.Key.INVALID, Status.Obj.USER, "uid", uid);
                return result;
            }

            UserVO userVO = new UserVO();
            userVO.setUid(uid);
            userVO.setPushTime(new Time(0));
            iUserDAO.insertUser(userVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "uservo", userVO);
        } catch (Exception e) {
            // need to Logging
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

}



