package com.jasper_report.model.domain;


import com.jasper_report.model.properties.Address;
import com.jasper_report.model.properties.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee",schema = "jasper_report")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empId")
    private long empId;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private double salary;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "asset_id")
//    private Asset asset;

}
