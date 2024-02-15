package com.adgan.persitence.projection;

import java.time.LocalDate;

public interface CattleSoldResume {
    Integer getIdCattle();
    String getNombre();
    LocalDate getFechaVenta();
    String getEdad();
    String getOwner();
    Integer getPeso();
    Integer getTotalNeto();
}
