package org.empit.bibliavetelkedo.wsgui.rest;

import org.empit.bibliavetelkedo.servicelayer.dto.RegistartionResponseDTO;
import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;
import org.empit.bibliavetelkedo.servicelayer.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.registry.infomodel.User;

@Path("registration")
public class RegistartionRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RegistartionResponseDTO login(UserDTO userDTO) {

        RegistartionResponseDTO response = new RegistartionResponseDTO();
        response.setSuccessfull(true);

        UserService instance = UserService.getInstance();
        if (instance.checkUsername(userDTO.getUsername())) {
            response.setSuccessfull(false);
            response.getMessgaes().add("username_exists");
        }
        if (response.isSuccessfull()) {
            instance.insert(userDTO);
        }
        return response;

    }

}
