package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Entity
@Table(name = "GAME_CARD")
public class GameCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Embedded
    private Problem problem;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean solved;

    public GameCard(User user, Problem problem) {
        ProblemChecker checker = new ProblemChecker();
        this.solved = problem.getAnswer() == checker.getAnswer(problem.getExpression());
        this.problem = problem;
        setBiDirectionalRelationWith(user);
    }

    private void setBiDirectionalRelationWith(User user) {
        this.user = user;
        user.getGameCards().add(this);
    }
}
