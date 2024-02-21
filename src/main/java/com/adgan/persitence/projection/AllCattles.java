package com.adgan.persitence.projection;

import java.time.LocalDate;

public interface AllCattles {
    Integer getIdCattle();
    Byte getEstado();
    LocalDate getFechaNacimiento();
    Integer getIdOwner();
    String getNombreOwner();
    String getApellido();
    String getContacto();
    String getCorreo();
}
