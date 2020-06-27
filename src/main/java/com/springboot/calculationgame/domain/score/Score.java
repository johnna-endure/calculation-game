package com.springboot.calculationgame.domain.score;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Entity
@Table(name = "SCORE")
public class Score {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_ID")
    private Long id;

    @Column(name = "TOTAL_SCORE")
    private int total = 0;

    public void scoreUp() {
        total += 10;
    }
}
