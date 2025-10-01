
package game.lupus.model;

import game.lupus.enums.Status;
import game.lupus.model.roles.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "players")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor 
public class Player {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;

    @Nonnull
    private boolean passed;


    @ManyToOne
    @JoinColumn(name = "id")
    private Role role;
    @Column(name = "is_alive")
    private boolean isAlive;
    @Column(name = "current_aura")
    private int aura;
    @Column(name = "current_magic")
    private int magic;
    @Column(name = "status")
    private ArrayList<Status> statuses = new ArrayList<>();


    public boolean hasPassed() {
        return passed;
    }
}

