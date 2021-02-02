package map.app.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import map.app.domain.BaseEntity;
import map.app.domain.entity.enums.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public User(){
        this.orders = new ArrayList<>();
        this.ownedGames = new ArrayList<>();
    }

    public User(@NonNull @Email String email,
                @NonNull @Length(min = 6, max = 30) String password,
                @NonNull String fullName, @NonNull Role role) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    @NonNull
    @NotNull
    @Email
    @Column(nullable = false, length = 100)
    private String email;

    @NonNull
    @NotNull
    @Length(min = 6, max = 30)
    private String password;

    @NonNull
    @NotNull
    @Column(nullable = false, length = 50)
    private String fullName;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "buyer")
    @ToString.Exclude
    private List<Order> orders;

    @ManyToMany
    @ToString.Exclude
    private List<Game> ownedGames;
}
