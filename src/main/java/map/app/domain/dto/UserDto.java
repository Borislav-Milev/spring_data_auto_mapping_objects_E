package map.app.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import map.app.domain.entity.Game;
import map.app.domain.entity.enums.Role;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

import static map.app.constant.Validations.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@NoArgsConstructor
public class UserDto extends UserRegisterDto {

    private Role role;


    public UserDto(@NotNull @Email(message = EMAIL_VIOLATION) String email,
                   @NotNull @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_VIOLATION) String password,
                   @NotNull(message = NAME_VIOLATION) @Length(max = 50, message = NAME_LENGTH_VIOLATION)
                           String fullName, Role role) {
        super(email, password, fullName);
        this.role = role;
    }
}
