package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.User;
import com.SistemaGestion.ShowroomX.Repository.IUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private IUser userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void Test_SaveUserWithAllFieldsOK() {
        User user = this.getUserWithAllFieldsOK();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User userResponse = userService.save(user);
        System.out.println(userResponse.getPassword());
        Assert.assertEquals(user, userResponse);
    }

    @Test
    public void Test_SaveUserWithFieldsNameNOK() {
        User user = this.getUserWithFieldNameNOK();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User userResponse = userService.save(user);
        Assert.assertNull(userResponse);
    }

    @Test
    public void Test_FindUserWithAllPermission() {
        this.getUserWithAllFieldsOK();


    }


    private User getUserWithAllFieldsOK() {
        User user = new User();
        user.setNameUser("msimon123");
        user.setPassword("simon");
        user.setStatus(true);
        user.setRoles("ADMIN");

        return user;
    }

    private User getUserWithFieldNameNOK() {
        User user = new User();
        user.setNameUser("msimon@_%");
        user.setPassword("simon");
        user.setStatus(true);
        user.setRoles("ADMIN");

        return user;
    }
}
