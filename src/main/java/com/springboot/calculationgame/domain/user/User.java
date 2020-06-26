package com.springboot.calculationgame.domain.user;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.score.Score;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SCORE_ID")
    private Score score;

    @OneToMany(mappedBy = "user")
    private List<GameCard> gameCards = new ArrayList<>();

    @Builder
    public User(String username, String password, Score score) {
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
