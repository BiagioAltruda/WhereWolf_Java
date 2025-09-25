package game.lupus.model;

import game.lupus.enums.Status;
import game.lupus.model.roles.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "players")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;

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
}
