package test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import java.sql.Time;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class UserDAOTest {

    @Autowired
    private IUserDAO IUserDAO;

    @Test
    public void Order1_InsertUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUid("usertest00");
        userVO.setPushTime(new Time(12));

        IUserDAO.insertUser(userVO);
    }

    @Test
    public void Order2_GetUser() throws Exception {
        System.out.println(IUserDAO.getUser("usertest00"));
    }
}
