package com.ze.codechallenge.partner.entity;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PARTNERS")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_generator")
    @SequenceGenerator(name="partner_generator", sequenceName = "seq_partners", allocationSize = 1)
    private Integer id;

    @Column(name = "TRADING_NAME")
    private String trandingName;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "ADDRESS", columnDefinition = "geometry")
    private Point address;

    @Column(name = "COVERAGE_AREA", columnDefinition = "geometry")
    private MultiPolygon coverageArea;
}
