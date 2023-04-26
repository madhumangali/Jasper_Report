package com.jasper_report.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AssetDetails", schema = "jasper_report")
public class AssetDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
    @Column(name = "assetDetailsId")
    private long assetDetailsId;

    @Column(name = "brandName")
    private int brandName;

    @Column(name = "ram")
    private int ram;

    @Column(name = "modelName")
    private int modelName;

}

