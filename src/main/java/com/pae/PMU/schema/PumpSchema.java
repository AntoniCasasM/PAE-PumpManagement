package com.pae.PMU.schema;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a pump")
public class PumpSchema {
    @ApiModelProperty(notes = "Id of the pump.", example = "1", required = true)
    private String pumpId;

    public String getPumpId() {
        return pumpId;
    }

    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
    }
}
