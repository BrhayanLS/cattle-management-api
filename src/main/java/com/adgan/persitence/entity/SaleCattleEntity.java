package com.adgan.persitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale_cattle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleCattleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_cattle", nullable = false)
    private Integer idSaleCattle;

    private Double peso;

    private Double totalNeto;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    @JsonIgnore
    private SaleEntity sale;

    @OneToOne
    @JoinColumn(name = "id_cattle")
    @JsonIgnore
    private CattleEntity cattle;

    @Override
    public String toString() {
        return "SaleCattleEntity{" +
                "idSaleCattle=" + idSaleCattle +
                ", sale=" + sale +
                ", cattle=" + cattle +
                ", peso=" + peso +
                ", totalNeto=" + totalNeto +
                ", total=" + total +
                '}';
    }
}
