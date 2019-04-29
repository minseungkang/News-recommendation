package weonforall.capstone.news_recommendation.controller;

import org.springframework.web.bind.annotation.*;
import weonforall.capstone.news_recommendation.domain.InterestVO;
import weonforall.capstone.news_recommendation.domain.KeywordVO;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.enums.Status;
import weonforall.capstone.news_recommendation.model.Result;
import weonforall.capstone.news_recommendation.persistence.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Keyword {
    @Inject
    IArticleHistoryDAO iArticleHistoryDAO;

    @Inject
    IArticleDAO iArticleDAO;

    @Inject
    IUserDAO userDAO;

    @Inject
    IInterestDAO interestDAO;

    @Inject
    IKeywordDAO keywordDAO;

    @RequestMapping(value = "/keyword/setKeywords", method = RequestMethod.POST)
    public @ResponseBody Result setKeywords(@RequestBody Map<String, Object> param) {
        Result result = null;

        try {
            System.out.println("/keyword/setKeyword");
            String uid = (String)param.getOrDefault("uid", null);
            ArrayList<String> keywordList = (ArrayList<String>)param.getOrDefault("keywords", null);

            if (uid == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

            if (keywordList == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

            UserVO userVO = userDAO.getUser(uid);
            if (userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            //ObjectMapper mapper = new ObjectMapper();
            //ArrayList<String> keywordList = mapper.readValue(keywordsJson, new TypeReference<ArrayList<String>>(){});

            for (String keyword : keywordList) {
                if (keyword == null) continue;

                KeywordVO keywordVO = keywordDAO.getkeywordVOByKeyword(keyword);
                if (keywordVO == null) continue;

                // interest Table에 추가.
                InterestVO interestVO = new InterestVO();
                interestVO.setKid(keywordVO.getKid());
                interestVO.setUid(userVO.getUid());

                // 존재여부 검사 후 없다면 추가.
                if (interestDAO.getInterestKeyword(interestVO) == null)
                    interestDAO.insertInterestKeyword(interestVO);

            }

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST);


        } catch (Exception e) {
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/keyword/getInitialKeywords", method = RequestMethod.GET)
    public @ResponseBody Result getInitialKeywords(@RequestParam(value = "uid") String _uid) {
        Result result = null;
        try {
            System.out.println("/keyword/getInitialKeywords");
            String uid = _uid;
            if (uid == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

            if (userDAO.getUser(uid) == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            List<String> keywordList = keywordDAO.getKeywords();
            System.out.println(keywordList);
            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "keywords", keywordList);

        } catch (Exception e) {
            System.out.println(e);
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }
}
