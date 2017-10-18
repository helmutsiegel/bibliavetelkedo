package org.empit.bibliavetelkedo.dal.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "AnswerBE")
@Table(name = "Answer")
@Cacheable(false)
public class AnswerBE implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameid")
    private GameBE game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionid")
    private QuestionBE question;

    @Column(name = "answer")
    private String answer;

    public AnswerBE(Long id) {
        this.id = id;
    }

    public AnswerBE() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameBE getGame() {
        return game;
    }

    public void setGame(GameBE game) {
        this.game = game;
    }

    public QuestionBE getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBE question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
