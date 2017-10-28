package org.empit.bibliavetelkedo.dal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "GameBE")
@Table(name = "Game")
@Cacheable(false)
public class GameBE implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserBE userBE;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerBE> answers;

    @ManyToMany
    @JoinTable(
            name = "Used_help",
            joinColumns = @JoinColumn(name = "gameId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "helpId", referencedColumnName = "id"))
    private Set<HelpBE> usedHelps;


    public GameBE(Long id) {
        this.id = id;
    }

    public GameBE() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBE getUserBE() {
        return userBE;
    }

    public void setUserBE(UserBE userBE) {
        this.userBE = userBE;
    }

    public List<AnswerBE> getAnswers() {
        return answers;
    }

    public List<AnswerBE> getCorrectAnswers() {
        return this.getAnswers().stream()
                .filter(a -> a.getQuestion().getCorrectAnswer()
                        .equals(a.getAnswer()))
                .collect(Collectors.toList());
    }


    public void setAnswers(List<AnswerBE> answers) {
        this.answers = answers;
    }

    public Set<HelpBE> getUsedHelps() {
        return usedHelps;
    }

    public void setUsedHelps(Set<HelpBE> usedHelps) {
        this.usedHelps = usedHelps;
    }

    public List<QuestionBE> getQuestions() {
        return this.getAnswers().stream().map(a -> a.getQuestion()).collect(Collectors.toList());
    }
}
