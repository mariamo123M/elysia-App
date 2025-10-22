@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthVerificationController {

    private final UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        userService.verifyEmail(email, code);
        return ResponseEntity.ok("Account verified successfully!");
    }
}
