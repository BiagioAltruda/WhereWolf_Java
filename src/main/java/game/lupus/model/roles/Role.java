package game.lupus.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_name",  discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Role {

    @Id
    @GeneratedValue
    private Integer id;

    public abstract void vote();
    public abstract void nightAction();
}
