package com.pae.PMU.controller;

import com.pae.PMU.entity.PumpEntity;
import com.pae.PMU.schema.InterventionSchema;
import com.pae.PMU.schema.InterventionSchemaGET;
import com.pae.PMU.schema.MaterialSchema;
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

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/")
@Api(value = "Stakeholders Recommender API", produces = MediaType.APPLICATION_JSON_VALUE)
public class PMUController {

    @Autowired
    PMUService PMUService;


    @RequestMapping(value = "pump", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a pump by ID", notes = "")
    public ResponseEntity getPump(@ApiParam(value = "The pump id.", example = "1", required = true) @RequestParam("pumpId") String pumpId) {
        PumpEntity toRet=PMUService.getPump(pumpId);
        return new ResponseEntity<>(toRet, HttpStatus.OK);
    }

    @RequestMapping(value = "pump", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new pump", notes = "")
    public ResponseEntity postPump(@ApiParam(value = "The pump id.", example = "1", required = true) @RequestBody PumpSchema pump) {
        PMUService.postPump(pump);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "pump", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update pump values", notes = "")
    public ResponseEntity updatePump(@ApiParam(value = "The pump id.", example = "1", required = true) @RequestBody PumpSchema pump) {
        PMUService.updatePump(pump);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "addAdjacentPumps", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add adjacent pumps", notes = "")
    public ResponseEntity updatePump(@ApiParam(value = "The pump to which the other pumps will be added as adjacent.", example = "1", required = true) @RequestParam("pumpId") String pumpId,@ApiParam(value = "The pumps, identified by their id, to add as adjacent.", example = "2", required = true) @RequestBody List<String> adjcentPumps) {
        PMUService.addAdjacentPumps(pumpId,adjcentPumps);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "postIntervention", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Post intervention", notes = "")
    public ResponseEntity postIntervention(@ApiParam(value = "The intervention schema.", required = true) @RequestBody InterventionSchema intervention) {
        PMUService.postIntervention(intervention);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "postMaterial", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Post a material, computes cost per unit", notes = "")
    public ResponseEntity postMaterial(@ApiParam(value = "The material schema.", required = true) @RequestBody MaterialSchema material) {
        PMUService.modifyMaterial(material);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getInterventions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all interventions of a pump", notes = "")
    public ResponseEntity getInterventions(@ApiParam(value = "The pump id.", example="1",required = true) @RequestParam String pumpId) {
        List<InterventionSchemaGET> res=PMUService.getInterventions(pumpId);
        return new ResponseEntity<List<InterventionSchemaGET>>(res,HttpStatus.OK);
    }

    @RequestMapping(value = "getInterventionsBetweenDates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all interventions of a pump between the dates spaciefied", notes = "")
    public ResponseEntity getInterventionsBetweenDates(@ApiParam(value = "The pump id.", required = true) @RequestParam String pumpId, @ApiParam(value = "From this date.",example = "2020-02-02T20:50:12.123Z", required = true) @RequestParam Date from, @ApiParam(value = "Up to this date.", required = true,example = "2020-02-02T20:50:12.123Z") @RequestParam Date to) {
        List<InterventionSchemaGET> res=PMUService.getInterventionsBetweenDates(pumpId,from,to);
        return new ResponseEntity<List<InterventionSchemaGET>>(res,HttpStatus.OK);
    }
}

