package map.app.domain.entity;

import lombok.*;
import map.app.domain.BaseEntity;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

import static map.app.constant.Validations.*;


@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @NonNull
    @NotNull
    @Column(nullable = false, unique = true)
    private String title;

    private String trailer;

    @URL(regexp = URL_REGEX, message = URL_VIOLATION)
    @Column(columnDefinition = "BLOB")
    private String imageThumbnail;

    @NonNull
    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Double size;

    @NonNull
    @NotNull
    @DecimalMin("0")
    @Column(nullable = false, columnDefinition = "DECIMAL (10,2)")
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate releaseDate;
}
