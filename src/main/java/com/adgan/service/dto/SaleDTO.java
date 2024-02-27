package com.adgan.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleDTO {
    private Integer idSale;
    private LocalDate fechaVenta;
    private Integer precioKilo;
    private Integer valorCamion;
    private Integer valorBascula;
    private List<SaleCattleDTO> saleCattles;
}

