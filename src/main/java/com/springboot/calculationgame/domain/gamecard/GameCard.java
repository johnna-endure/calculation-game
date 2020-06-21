package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Entity
public class GameCard {

    @Id @GeneratedValue
    @Column(name = "CARD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Embedded
    private Problem problem;

    @Column(nullable = false)
    private boolean solved;

    @Column(nullable = false)
    private long score;

    @Builder
    public GameCard(Problem problem, boolean solved, long score) {
        this.problem = problem;
        this.solved = solved;
        this.score = score;
    }

    public void setUser(User user){
        this.user = user;
    }
}
