package weonforall.capstone.news_recommendation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
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
    IUserDAO userDAO;

    @Inject
    IInterestDAO interestDAO;

    @Inject
    IKeywordDAO keywordDAO;

    private final String RECOMMEND_SERVER_URL = "http://ec2-13-209-79-247.ap-northeast-2.compute.amazonaws.com:5555/suggest/";

    @RequestMapping(value = "/keyword/setKeywords", method = RequestMethod.POST)
    public @ResponseBody Result setKeywords(@RequestBody Map<String, Object> param) {
        Result result;

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

            // recommend API server에 추천 요청.
            AsyncRestTemplate restTemplate = new AsyncRestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpParam = new HttpEntity<>(headers);
            restTemplate.postForEntity(RECOMMEND_SERVER_URL + uid, httpParam, String.class);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST);


        } catch (Exception e) {
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/keyword/getInitialKeywords", method = RequestMethod.GET)
    public @ResponseBody Result getInitialKeywords(@RequestParam(value = "uid") String _uid) {
        Result result;
        try {
            System.out.println("/keyword/getInitialKeywords");
            if (_uid == null) {
                result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
                return result;
            }

            if (userDAO.getUser(_uid) == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            List<String> keywordList = keywordDAO.getKeywords();
            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "keywords", keywordList);

        } catch (Exception e) {
            System.out.println(e);
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }
}
