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

@RestController
public class Setting {
    @Inject
    IArticleHistoryDAO iArticleHistoryDAO;

    @Inject
    IArticleDAO iArticleDAO;

    @Inject
    IUserDAO iUserDAO;

    @RequestMapping(value = "/setting/setPushTime", method = RequestMethod.POST)
    public @ResponseBody Result setPushTime(@RequestBody Map<String, Object> param) {
        System.out.println("/setting/setPushTime");
        String uid = (String)param.getOrDefault("uid", null);
        int hour = (int)param.getOrDefault("hour", 9);
        int minute = (int)param.getOrDefault("minute", 0);

        Result result = null;
        try {
            if (uid == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM, "uid", uid);
                return result;
            }

            UserVO userVO = iUserDAO.getUser(uid);
            if (userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            if (hour >= 24 || hour < 0) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM, "hour", hour);
                return result;
            }

            if (minute >= 60 || minute < 0) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM, "minute", minute);
                return result;
            }

            Time newTime = new Time(hour, minute, 0);
            userVO.setPushTime(newTime);
            iUserDAO.updateUser(userVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "uservo", userVO);

        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }

        return result;
    }
}
