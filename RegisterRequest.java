@Data
public class RegisterRequest {
    @Email
    private String email;
    private String password;
    private Role role; // მსმენელი ან არტისტი

}
