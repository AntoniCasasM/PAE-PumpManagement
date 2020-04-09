package com.pae.PMU.schema;

import com.pae.PMU.entity.PumpInterventionEntity;
import io.swagger.annotations.ApiModelProperty;

public class InterventionSchemaGET extends InterventionSchema {
    @ApiModelProperty(notes = "Price of the intervention.", example = "400", required = true)
    double price;

    public InterventionSchemaGET(PumpInterventionEntity intervention,Double price) {
        super(intervention);
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
