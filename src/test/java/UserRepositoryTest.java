

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jdbSpringBootCaseStudy.dao.UserRepository;
import com.jdbSpringBootCaseStudy.model.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
    @Before
    public void setUp() throws Exception {
    	
        User user1= new User(0, "k.j@mail.com", "Kevin", "Johnson", "pass", "kjohnson");
        User user2= new User(0, "lb@mail.com", "Larry",  "Bird", "pass", "lbird");
        //save user, verify has ID value after save
        assertNull(user1.getuId());
        assertNull(user2.getuId());//null before save
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        assertNotNull(user1.getuId());
        assertNotNull(user2.getuId());
    }
    

}