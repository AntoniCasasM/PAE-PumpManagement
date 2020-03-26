package com.pae.PMU.service;

import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.repository.PumpRepository;
import com.pae.PMU.schema.PumpSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PMUService {
    @Autowired
    PumpRepository pumpRepository;

    public PumpEntity getPump(String id) {
        return pumpRepository.getOne(id);
    }
    public PumpEntity postPump(PumpSchema pump) {
        PumpEntity entity=new PumpEntity(pump);
        return pumpRepository.save(entity);
    }

    public void addAdjacentPumps(String pumpId, List<String> adjacentPumps) {
        PumpEntity mainPump=pumpRepository.getOne(pumpId);
        List<PumpEntity> secondaryPumps=pumpRepository.findAllById(adjacentPumps);
        Set<String> currentAdjacent=mainPump.getAdjacentPumps();
        currentAdjacent.addAll(adjacentPumps);
        mainPump.setAdjacentPumps(currentAdjacent);
        pumpRepository.save(mainPump);
        for (PumpEntity pump:secondaryPumps) {
            if (!pump.getAdjacentPumps().contains(pumpId)) {
                Set<String> adjacent=pump.getAdjacentPumps();
                adjacentPumps.add(pumpId);
                pump.setAdjacentPumps(adjacent);
                pumpRepository.save(pump);
            }
        }

    }

    public void updatePump(PumpSchema pump) {
        PumpEntity oldPump=pumpRepository.getOne(pump.getPumpId());
        oldPump.update(pump);
    }
}
