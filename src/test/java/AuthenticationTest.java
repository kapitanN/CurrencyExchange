/**
 * Created by nikita on 05.01.2017.
 */
import com.dao.UserDAO;
import com.dao.UserEntity;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthenticationTest {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationTest.class);


    private static final String CORRECT_NAME = "nikita";
    private static final String CORRECT_EMAIL = "nikita@com";
    private static final String INCORRECT_EMAIL = "incorrect@com";
    private static final String CORRECT_PASSWORD = "123";
    private static final String INCORRECT_PASSWORD = "incorrect";

    private UserDAO userDAO;

    @Before
    public void init(){
        this.userDAO = new UserDAO();
    }

    @Test
    public void getUserTest(){
        LOGGER.info("get user test");
        UserEntity user = userDAO.getUser(CORRECT_EMAIL);
        assertEquals(CORRECT_NAME, user.getName());
    }

    @Test
    public void checkUserTest(){
        LOGGER.info("check user test");
        boolean check = userDAO.checkUser(CORRECT_EMAIL,CORRECT_PASSWORD);
        assertEquals(true,check);
    }

    @Test
    public void checkUserIncorrectPasswordTest(){
        LOGGER.info("check user test");
        boolean check = userDAO.checkUser(CORRECT_EMAIL,INCORRECT_PASSWORD);
        assertEquals(false,check);
    }

    @Test
    public void checkUserIncorrectEmailAndPasswordTest(){
        LOGGER.info("check user test");
        boolean check = userDAO.checkUser(INCORRECT_EMAIL,INCORRECT_PASSWORD);
        assertEquals(false,check);
    }
}
