import com.example.repositories.UserRepository;
import com.example.service.Impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdatePassword() {
        String password = "newPassword";
        Long id = 1L;

        userService.updatePassword(password, id);

        verify(userRepository, times(1)).updatePassword(password, id);
    }

    @Test
    public void testUpdateUser() {
        String email = "test@example.com";
        String username = "testUser";
        String phone = "1234567890";
        String documento = "123456";
        Long id = 1L;

        userService.updateUser(email, username, phone, documento, id);

        verify(userRepository, times(1)).updateUser(email, username, phone, documento, id);
    }
}

