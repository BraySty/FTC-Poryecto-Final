package com.ftc.flightcontrol.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase se encarga de contener String con mensajes para ser enviados por HTTP.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {

    String content;

}
