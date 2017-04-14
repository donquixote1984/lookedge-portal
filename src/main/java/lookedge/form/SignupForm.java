package lookedge.form;

import lombok.Getter;
import lombok.Setter;
import lookedge.annotations.FieldMatch;
import lookedge.model.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
@Getter
@Setter
public class SignupForm {

    private static final String NOT_BLANK_MESSAGE = "Value should not be blank";
    private static final String EMAIL_MESSAGE = "You must input correct email format";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    @Email(message = SignupForm.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String password;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String confirmPassword;

    public Account createAccount() {
        return new Account(getEmail(), getPassword(), "ROLE_USER");
    }

}
