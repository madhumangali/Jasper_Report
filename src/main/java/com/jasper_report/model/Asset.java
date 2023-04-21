package com.jasper_report.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset", schema = "jasper_report")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assetId")
    private long assetId;

    @Column(name = "assetNumber")
    private int assetNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "location")
    private String location;

}
