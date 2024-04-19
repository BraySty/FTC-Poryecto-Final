package com.ftc.flightcontrol.security.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String dni;
    String userName;
    String apellido;
    String correo;
    String password;

}
