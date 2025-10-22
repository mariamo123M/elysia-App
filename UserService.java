@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setStatus(Status.PENDING);
        user.setVerificationCode(generateCode());

        userRepository.save(user);
        sendVerificationEmail(user);
    }

    private String generateCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit code
    }

    private void sendVerificationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Verification Code");
        message.setText("Your verification code is: " + user.getVerificationCode());
        mailSender.send(message);
    }

    public void verify(String email, String code) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getVerificationCode().equals(code)) {
            throw new RuntimeException("Invalid verification code");
        }

        user.setStatus(Status.ACTIVE);
        user.setVerificationCode(null);
        userRepository.save(user);
    }
}