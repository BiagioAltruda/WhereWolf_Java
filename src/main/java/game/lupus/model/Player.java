package game.lupus.model;

import game.lupus.enums.Status;
import game.lupus.model.roles.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

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
    @NonNull
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
	public boolean isPassed() {
		// TODO Auto-generated method stub
		return false;
	}
	public Object setPassed(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
