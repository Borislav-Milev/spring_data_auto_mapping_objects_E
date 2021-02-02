package map.app.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static map.app.constant.Validations.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class GameAddDto {

    @NonNull
    @NotNull
    @Pattern(regexp = TITLE_REGEX, message = TITLE_VIOLATION)
    @Length(min = 3, max = 100)
    private String title;

    @NonNull
    @NotNull
    @Min(value = 0, message = SIZE_VIOLATION)
    private Double size;

    @NonNull
    @NotNull
    @DecimalMin(value = "0", message = PRICE_VIOLATION)
    private BigDecimal price;

    @NonNull
    @NotNull
    @Length(min = 11, max = 11, message = TRAILER_VIOLATION)
    private String trailer;

    @URL(regexp = URL_REGEX, message = URL_VIOLATION)
    private String imageThumbnail;

    private String description;

    @NonNull
    @NotNull
    @PastOrPresent
    private LocalDate releaseDate;
}
