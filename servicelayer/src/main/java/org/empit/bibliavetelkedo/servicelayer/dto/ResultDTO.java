package org.empit.bibliavetelkedo.servicelayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.empit.bibliavetelkedo.dal.entity.HelpEnum;

import java.util.List;

public class ResultDTO {

    @JsonProperty("fname")
    private String firstName;

    @JsonProperty("lname")
    private String lastName;

    @JsonProperty("uname")
    private String username;

    @JsonProperty("level")
    private Long level;

    @JsonProperty("helps")
    private List<HelpEnum> usedHelps;

    public List<HelpEnum> getUsedHelps() {
        return usedHelps;
    }

    public void setUsedHelps(List<HelpEnum> usedHelps) {
        this.usedHelps = usedHelps;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
