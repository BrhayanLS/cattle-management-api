package com.adgan.persitence.entity;

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

    /*@Column(name = "id_sale", nullable = false, insertable = false, updatable = false)
    private Integer idSale;

    @Column(name = "id_cattle", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer idCattle;*/

    private Double peso;

    private Double totalNeto;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    private SaleEntity sale;

    @OneToOne
    @JoinColumn(name = "id_cattle")
    private CattleEntity cattle;

    public SaleCattleEntity(Double peso, Double totalNeto, Double total, CattleEntity cattle) {
        this.peso = peso;
        this.totalNeto = totalNeto;
        this.total = total;
        this.cattle = cattle;
    }

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
