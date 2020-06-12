package com.ze.codechallenge.partner.service;

import com.ze.codechallenge.common.exception.PartnerAlreadyExistsException;
import com.ze.codechallenge.common.exception.PartnerNotFoundException;
import com.ze.codechallenge.partner.entity.Partner;
import com.ze.codechallenge.partner.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    private final PartnerRepository repository;

    public PartnerService(PartnerRepository repository) {
        this.repository = repository;
    }

    public void save(Partner partner){
        if(repository.existsByDocument(partner.getDocument())){
            throw new PartnerAlreadyExistsException();
        }
        repository.save(partner);
    }

    public Partner searchById(Integer id){
        return repository.findById(id).orElseThrow(PartnerNotFoundException::new);
    }

    public List<Partner> search(Double lat, Double lng){
        return this.repository.searchNearts(lat, lng);
    }
}
