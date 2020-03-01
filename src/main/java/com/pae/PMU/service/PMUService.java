package com.pae.PMU.service;

import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.repository.PumpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PMUService {
    @Autowired
    PumpRepository pumpRepository;

    public PumpEntity getPump(String id) {
        return pumpRepository.getOne(id);
    }
    public PumpEntity postPump(PumpEntity pump) {
        return pumpRepository.save(pump);
    }
}
