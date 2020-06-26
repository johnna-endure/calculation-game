package com.springboot.calculationgame.domain.score;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @ToString
@NoArgsConstructor
@Entity
public class Score {

    @Id @GeneratedValue
    @Column(name = "SCORE_ID")
    private Long id;

    @Column(name = "TOTAL_SCORE")
    private int total = 0;

    public void scoreUp() {
        total += 10;
    }
}
