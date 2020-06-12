package com.ze.codechallenge.partner.mapper;

import com.ze.codechallenge.common.mapper.Mapper;
import com.ze.codechallenge.partner.entity.Partner;
import com.ze.codechallenge.partner.models.PartnerRequestDTO;
import com.ze.codechallenge.partner.models.PartnerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper implements Mapper<Partner, PartnerRequestDTO, PartnerResponseDTO> {

    @Override
    public PartnerResponseDTO convertTransferObjectResponse(Partner partner) {
        return PartnerResponseDTO.builder()
                .id(partner.getId())
                .tradingName(partner.getTrandingName())
                .ownerName(partner.getOwnerName())
                .document(partner.getDocument())
                .address(partner.getAddress())
                .coverageArea(partner.getCoverageArea())
                .build();
    }

    @Override
    public Partner convertEntity(PartnerRequestDTO request) {
        return Partner.builder()
                .trandingName(request.getTradingName())
                .ownerName(request.getOwnerName())
                .document(request.getDocument())
                .address(request.getAddress())
                .coverageArea(request.getCoverageArea())
                .build();
    }


}
