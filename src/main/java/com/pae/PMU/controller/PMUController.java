package com.pae.PMU.controller;

import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.schema.PumpSchema;
import com.pae.PMU.service.PMUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/")
@Api(value = "Stakeholders Recommender API", produces = MediaType.APPLICATION_JSON_VALUE)
public class PMUController {

    @Autowired
    PMUService PMUService;


    @RequestMapping(value = "sampleGET", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a pump", notes = "")
    public ResponseEntity sampleGET(@ApiParam(value = "The pump id.", example = "1", required = true) @RequestParam("pumpId") String pumpId) {
        PumpEntity toRet=PMUService.getPump(pumpId);
        return new ResponseEntity<>(toRet, HttpStatus.OK);
    }

    @RequestMapping(value = "samplePOST", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Post some information", notes = "")
    public ResponseEntity samplePOST(@ApiParam(value = "The pump id.", example = "1", required = true) @RequestBody PumpSchema pump) {
        PumpEntity entity=new PumpEntity();
        entity.setId(pump.getPumpId());
        PMUService.postPump(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

