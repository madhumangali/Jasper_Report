package com.jasper_report.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address", schema = "jasper_report")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private long addressId;

    @Column(name = "houseNumber")
    private String houseNumber;

    @Column(name="city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name= "pinCode")
    private long pinCode;


}
