import static jdk.jfr.internal.jfc.model.Constraint.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private UserService userService;

    @Test
    void register_shouldSaveUserAndSendEmail() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setRole(Role.LISTENER);

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        userService.register(request);

        verify(userRepository, times(1)).save(any(User.class));
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}