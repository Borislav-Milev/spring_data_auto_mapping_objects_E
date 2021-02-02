package map.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static map.app.constant.Validations.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {

    @NotNull
    @Email(message = EMAIL_VIOLATION)
    private String email;

    @NotNull
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_VIOLATION)
    private String password;
}
