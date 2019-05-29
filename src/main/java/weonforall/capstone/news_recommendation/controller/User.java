package weonforall.capstone.news_recommendation.controller;

import org.springframework.web.bind.annotation.*;
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
    IUserDAO iUserDAO;


    @RequestMapping(value = "/user/setToken", method = RequestMethod.POST)
    public @ResponseBody Result setToken(@RequestBody Map<String, Object> param) {
        Result result;

        try {
            System.out.println("/user/setToken");
            String token = (String)param.getOrDefault("token", null);
            String uid = (String)param.getOrDefault("uid", null);

            if (uid == null || token == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

            UserVO userVO = iUserDAO.getUser(uid);
            if (userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            userVO.setToken(token);
            iUserDAO.updateUser(userVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "uservo", userVO);
        } catch (Exception e) {
            // TODO need to logging
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/user/initUser", method = RequestMethod.POST)
    public @ResponseBody Result initUser(@RequestBody Map<String, Object> param) {

        Result result;

        try {
            System.out.println("/user/initUser");
            String uid = UUID.randomUUID().toString();

            String token = (String)param.getOrDefault("token", null);

            if (token == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

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
            userVO.setToken(token);
            iUserDAO.insertUser(userVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "uservo", userVO);
        } catch (Exception e) {
            // TODO need to Logging
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

}



