package map.app.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import map.app.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public Order() {
        this.games = new ArrayList<>();
    }

    @ManyToOne
    @ToString.Exclude
    private User buyer;

    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Game> games;
}
