package com.ze.codechallenge.partner.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.ze.codechallenge.common.json.MultiPolygonDeserializer;
import com.ze.codechallenge.common.json.PointDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PartnerReques")
public class PartnerRequestDTO {

    @NotNull(message = "Trading name is required")
    @ApiModelProperty(value = "Trading name", required=true)
    private String tradingName;

    @NotNull(message = "Owner name is required")
    @ApiModelProperty(value = "Owner name.", required=true)
    private String ownerName;

    @NotNull(message = "Document is required")
    @ApiModelProperty(value = "Document - CNPJ ", required=true)
    private String document;

    @NotNull(message = "Coverage area is required")
    @ApiModelProperty(value = "Coverage area", required=true)
    @JsonDeserialize(using = MultiPolygonDeserializer.class)
    private MultiPolygon coverageArea;

    @NotNull(message = "Address area is required")
    @ApiModelProperty(value = "Address", required=true)
    @JsonDeserialize(using = PointDeserializer.class)
    private Point address;
}
