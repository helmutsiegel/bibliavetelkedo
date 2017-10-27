package org.empit.bibliavetelkedo.servicelayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class QuestionDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("question")
    private String question;

    @JsonProperty("answerA")
    private String answerA;

    @JsonProperty("answerB")
    private String answerB;

    @JsonProperty("answerC")
    private String answerC;

    @JsonProperty("answerD")
    private String answerD;

    @JsonProperty("canNext")
    private boolean canNext;

    @JsonProperty("canHalf")
    private boolean canHalf;

    @JsonProperty("canFault")
    private boolean canFault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public boolean isCanNext() {
        return canNext;
    }

    public void setCanNext(boolean canNext) {
        this.canNext = canNext;
    }

    public boolean isCanHalf() {
        return canHalf;
    }

    public void setCanHalf(boolean canHalf) {
        this.canHalf = canHalf;
    }

    public boolean isCanFault() {
        return canFault;
    }

    public void setCanFault(boolean canFault) {
        this.canFault = canFault;
    }
}
