package org.empit.bibliavetelkedo.dal.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "HelpBE")
@Table(name = "Help")
@Cacheable(false)
public class HelpBE implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private HelpEnum help;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HelpEnum getHelp() {
        return help;
    }

    public void setHelp(HelpEnum help) {
        this.help = help;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelpBE helpBE = (HelpBE) o;

        return id != null ? id.equals(helpBE.id) : helpBE.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
