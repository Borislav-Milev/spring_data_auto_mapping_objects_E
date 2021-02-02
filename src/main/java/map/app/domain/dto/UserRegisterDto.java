package map.app.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static map.app.constant.Validations.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@Data
public class UserRegisterDto extends UserLoginDto {

    @NotNull(message = NAME_VIOLATION)
    @Length(max = 50, message = NAME_LENGTH_VIOLATION)
    private String fullName;

    public UserRegisterDto(@NotNull @Email(message = EMAIL_VIOLATION) String email,
                           @NotNull @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_VIOLATION) String password,
                           @NotNull(message = NAME_VIOLATION)
                           @Length(max = 50, message = NAME_LENGTH_VIOLATION) String fullName) {
        super(email, password);
        this.fullName = fullName;
    }
}
