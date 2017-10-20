package org.empit.bibliavetelkedo.servicelayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.AssertFalse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helmut on 19.04.2017.
 */
public class RegistartionResponseDTO implements Serializable{
    @JsonProperty("successfull")
    private boolean successfull;

    @JsonProperty("messages")
    private List<String> messgaes = new ArrayList<String>();

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }

    public List<String> getMessgaes() {
        return messgaes;
    }

    public void setMessgaes(List<String> messgaes) {
        this.messgaes = messgaes;
    }
}
