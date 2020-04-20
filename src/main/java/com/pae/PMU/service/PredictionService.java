package com.pae.PMU.service;

import com.pae.PMU.NLP.CosineSimilarity;
import com.pae.PMU.NLP.TfIdf;
import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.entity.PumpInterventionEntity;
import com.pae.PMU.repository.PumpInterventionRepository;
import com.pae.PMU.schema.FailureSchema;
import com.pae.PMU.schema.InterventionSchema;
import com.pae.PMU.schema.InterventionSchemaGET;
import com.pae.PMU.schema.TrainModelsSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class PredictionService {
    @Autowired
    PumpInterventionRepository pumpInterventionRepository;

    public FailureSchema predict(PumpEntity pump) throws IOException {
        List<PumpInterventionEntity> interventions=pumpInterventionRepository.findByType(pump.getType());
        PumpInterventionEntity auxiliar=new PumpInterventionEntity();
        String remarks="";
        for (String s:pump.getRemarks()) {
            remarks+=" "+s;
        }
        auxiliar.setRemarks(remarks);
        Date current=new Date();
        auxiliar.setInterventionDate(current);
        auxiliar.setPumpId(pump.getId());
        interventions.add(auxiliar);
        PumpInterventionEntity closest=null;
        Double distance=null;
        TfIdf tfidf=new TfIdf(0.0);
        Map<String, Map<String, Double>> tfidfMatrix=tfidf.computeTFIDF(interventions);
        String auxId=current.toString()+pump.getId();
        for (PumpInterventionEntity intervention:interventions) {
            String interiorId=intervention.getInterventionDate().toString()+intervention.getPumpId();
            Double cosSim=CosineSimilarity.cosineSimilarity(tfidfMatrix.get(auxId),tfidfMatrix.get(interiorId));
            if (distance==null || distance<cosSim) {
                distance=cosSim;
                closest=intervention;
            }
        }
        List<InterventionSchemaGET> orderedByDate=getInterventions(closest.getPumpId());
        Date currentFailureDate=orderedByDate.get(0).getFailureDate();
        for (InterventionSchemaGET inter:orderedByDate) {
            if (inter.getFailureDate().compareTo(closest.getFailureDate())<0) currentFailureDate=inter.getFailureDate();
        }
        FailureSchema failure=new FailureSchema();
        long diff = closest.getFailureDate().getTime() - currentFailureDate.getTime();
        failure.setEstimatedDate(new Date(current.getTime()+diff));
        return failure;
    }


    public List<InterventionSchemaGET> getInterventions(String pumpId) {
        List<PumpInterventionEntity> interventions=pumpInterventionRepository.findByPumpId(pumpId);
        List<InterventionSchemaGET> result=new ArrayList<>();
        for(PumpInterventionEntity intervention:interventions) {
            InterventionSchemaGET aux=new InterventionSchemaGET(intervention);
            result.add(aux);
        }
        Collections.sort(result);
        return result;
    }

}
