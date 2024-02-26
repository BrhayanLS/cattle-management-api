package com.adgan.persitence.projection;

import java.time.LocalDate;

public interface SalesCattles {
    Integer getIdSale();
    Byte getEstado();
    LocalDate getFechaVenta();
    Integer getPrecioKilo();
    Integer getValorBascula();
    Integer getValorCamion();
    Integer peso();
    Integer getTotal();
    Integer getTotalNeto();
    String getNombre();
}
