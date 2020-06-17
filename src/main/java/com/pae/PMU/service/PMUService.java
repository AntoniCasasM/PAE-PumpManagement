package com.pae.PMU.service;

import com.pae.PMU.entity.MaterialEntity;
import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.entity.PumpInterventionEntity;
import com.pae.PMU.repository.MaterialRepository;
import com.pae.PMU.repository.PumpInterventionRepository;
import com.pae.PMU.repository.PumpRepository;
import com.pae.PMU.schema.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PMUService {
    @Autowired
    PumpRepository pumpRepository;
    @Autowired
    PumpInterventionRepository pumpInterventionRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    PredictionService predictionService;
    Double costPerKm=0.1;

    public PumpEntity getPump(String id) {
        return pumpRepository.getOne(id);
    }
    public PumpEntity postPump(PumpSchema pump) {
        PumpEntity entity=new PumpEntity(pump);
        return pumpRepository.save(entity);
    }

    public void addAdjacentPumps(String pumpId, List<String> adjacentPumps) {
        PumpEntity mainPump=pumpRepository.getOne(pumpId);
        List<PumpEntity> secondaryPumps=new ArrayList<>();
        for (String s:adjacentPumps)secondaryPumps.add(pumpRepository.getOne(s));
        Set<String> currentAdjacent=mainPump.getAdjacentPumps();
        currentAdjacent.addAll(adjacentPumps);
        mainPump.setAdjacentPumps(currentAdjacent);
        pumpRepository.save(mainPump);
        for (PumpEntity pump:secondaryPumps) {
            if (!pump.getAdjacentPumps().contains(pumpId)) {
                Set<String> adjacent=pump.getAdjacentPumps();
                adjacent.add(pumpId);
                pump.setAdjacentPumps(adjacent);
                pumpRepository.save(pump);
            }
        }
        pumpRepository.flush();
    }

    public void updatePump(PumpSchema pump) {
        PumpEntity oldPump=pumpRepository.getOne(pump.getPumpId());
        oldPump.update(pump);
    }

    public void postIntervention(InterventionSchema intervention) {
        PumpInterventionEntity interventionEntity= new PumpInterventionEntity(intervention);
        for(MaterialSchema material:intervention.getMaterials()) {
            if (!materialRepository.existsById(material.getMaterial())) {
                MaterialEntity newMaterial=new MaterialEntity(material);
                materialRepository.save(newMaterial);
            }
        }
        interventionEntity.setInterventionPrice(getPriceIntervention(interventionEntity));
        pumpInterventionRepository.save(interventionEntity);
    }

    public List<InterventionSchemaGET> getInterventions(String pumpId) {
        List<PumpInterventionEntity> interventions=pumpInterventionRepository.findByPumpId(pumpId);
        List<InterventionSchemaGET> result=new ArrayList<>();
        for(PumpInterventionEntity intervention:interventions) {
            Double price=getPriceIntervention(intervention);
            InterventionSchemaGET aux=new InterventionSchemaGET(intervention,price);
            result.add(aux);
        }
        Collections.sort(result);
        return result;
    }

    public void modifyMaterial(MaterialSchema material) {
        if (materialRepository.existsById(material.getMaterial())) {
            MaterialEntity item=materialRepository.getOne(material.getMaterial());
            item.setCostPerUnit(material.getTotalCost()/material.getUnits());
            materialRepository.save(item);
        }
        else {
            MaterialEntity item=new MaterialEntity(material);
            materialRepository.save(item);
        }
    }

    public Double getPriceIntervention(PumpInterventionEntity intervention){
        Double result=0.0;
        result+=intervention.getWorkers()*intervention.getCostPerWorker();
        result+=intervention.getDistanceTravelled()*costPerKm;
        for (Pair<String,Double> item:intervention.getMaterials()) {
            MaterialEntity material=materialRepository.getOne(item.getKey());
            result+=material.getCostPerUnit()*item.getValue();
        }
        return result;
    }

    public List<InterventionSchemaGET> getInterventionsBetweenDates(String pumpId, Date from, Date to) {
        List<InterventionSchemaGET> toFix=getInterventions(pumpId);
        List<InterventionSchemaGET> result=new ArrayList<>();
        for(InterventionSchemaGET intervention:toFix) {
            if (intervention.getInterventionDate().compareTo(from)<0 && intervention.getInterventionDate().compareTo(to)>0) {
                result.add(intervention);
            }
        }
        return result;
    }

    public FailureSchema getPredictedFailureDate(String pumpId) throws IOException {
        PumpEntity pump=pumpRepository.getOne(pumpId);
        FailureSchema failure=predictionService.predict(pump);
        return failure;
    }

    public Set<String> getAllIds() throws IOException {
        Set<String> ids=new HashSet<>();
        for (PumpEntity pump: pumpRepository.findAll()) {
            ids.add(pump.getId());
        }
        return ids;
    }
}
