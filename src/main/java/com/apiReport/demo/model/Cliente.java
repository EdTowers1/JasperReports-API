package com.apiReport.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "munmacli")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Long rowId;

    @Column(name = "codcli", length = 10)
    private String codcli;

    @Column(name = "nomcli", length = 60)
    private String nomcli;

    @Column(name = "dircli", length = 35)
    private String dircli;

    @Column(name = "telcli", length = 12)
    private String telcli;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "perjurid")
    private Boolean perjurid;

    @Column(name = "cupo_cre", precision = 15, scale = 2)
    private BigDecimal cupoCre;

    @Column(name = "ult_com")
    private LocalDate ultCom;

    @Column(name = "nivelaut")
    private Integer nivelaut;
}
