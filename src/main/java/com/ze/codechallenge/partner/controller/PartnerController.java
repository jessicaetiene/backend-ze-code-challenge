package com.ze.codechallenge.partner.controller;

import com.ze.codechallenge.partner.mapper.PartnerMapper;
import com.ze.codechallenge.partner.models.PartnerRequestDTO;
import com.ze.codechallenge.partner.models.PartnerResponseDTO;
import com.ze.codechallenge.partner.service.PartnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("partners")
@Api(value = "Partners")
public class PartnerController {

    private final PartnerService service;
    private final PartnerMapper mapper;

    public PartnerController(PartnerService service, PartnerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping()
    @ApiOperation(value = "Create a new partner")
    ResponseEntity<Void> create(@ApiParam(value="Data of partners")
                                @RequestBody @Valid PartnerRequestDTO pdvs){
        service.save(mapper.convertEntity(pdvs));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get partner by id")
    ResponseEntity<PartnerResponseDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(mapper.convertTransferObjectResponse(service.searchById(id)));
    }

    @GetMapping("/nearest")
    @ApiOperation(value = "Search partner")
    ResponseEntity<List<PartnerResponseDTO>> search(@RequestParam Double lat, @RequestParam Double lng){
        return ResponseEntity.ok(mapper.convertTransferObjectResponse(service.search(lat, lng)));
    }
}
