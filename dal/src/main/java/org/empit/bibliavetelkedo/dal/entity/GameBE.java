package org.empit.bibliavetelkedo.dal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "Used_help",
            joinColumns = @JoinColumn(name = "helpid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gameid", referencedColumnName = "id"))
    private Set<HelpBE> usedHelps;


    public GameBE(Long id) {
        this.id = id;
    }

    public GameBE(){

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
}
