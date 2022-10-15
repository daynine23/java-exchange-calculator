package com.coralogix.calculator.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="Exchange")
@Entity
@Data
public class Exchange {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "origin_currency")
    private String originCurrency;

    @Column(name = "final_currency")
    private String finalCurrency;

    @Column(name = "create_date")
    private String date;

    @Column(name = "cvalue")
    private String value;


}
