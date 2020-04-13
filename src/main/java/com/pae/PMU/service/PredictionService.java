package com.pae.PMU.service;

import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.entity.PumpInterventionEntity;
import com.pae.PMU.repository.PumpInterventionRepository;
import com.pae.PMU.schema.FailureSchema;
import com.pae.PMU.schema.TrainModelsSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class PredictionService {
    @Autowired
    PumpInterventionRepository pumpInterventionRepository;

    public FailureSchema predict(PumpEntity pump) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Date> result = restTemplate.postForEntity("localhost:1454/predict",pump, Date.class);
        FailureSchema failure=new FailureSchema();
        failure.setEstimatedDate(result.getBody());
        return failure;
    }

    @Scheduled(cron = "0 12 * * 0")
    public String train() {
        List<PumpInterventionEntity> interventions=pumpInterventionRepository.findAll();
        TrainModelsSchema train=new TrainModelsSchema(interventions);
        String accuracy=callTrain(train);
        return accuracy;
    }

    private String callTrain(TrainModelsSchema train) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity("localhost:1454/train",train, String.class);
        return result.toString();
    }
}
