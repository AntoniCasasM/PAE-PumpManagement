package com.pae.PMU.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pae.PMU.schema.PumpSchema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
    @Table(name = "PumpEntity")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class PumpEntity implements Serializable {

        @Id
        @Column(length = 300)
        private String id;
        @Column(length = 300)
        private String name;
        @Column(length = 300)
        private String territory;
        private String builtBy;
        private String maintainedBy;
        private boolean underPMU;
        private String location;
        private String northings;
        private String eastings;
        private String type;
        private String model;
        private String pumpSize;
        @ElementCollection
        private List<String> remarks;
        private Double runningBalance;
        @ElementCollection
        private List<String> comiteeContracts;
        private String drillingCompany;
        private String drillingYear;
        private Double boreholeDepth;
        private Double boreholeDiameter;
        private Double staticWaterLevel;
        private Double dynamicWaterLevel;
        private Double testedYield;
        private Double inlet;
        private Double inletYieldTest;
        private String waterQuality;

        @ElementCollection
        private Set<String> adjacentPumps;

        public PumpEntity() {

        }

        public PumpEntity(PumpSchema pump) {
            id=pump.getPumpId();
            name=pump.getName();
            territory=pump.getTerritory();
            builtBy=pump.getBuiltBy();
            maintainedBy=pump.getMaintainedBy();
            underPMU=pump.isUnderPMU();
            location=pump.getLocation();
            northings=pump.getNorthings();
            eastings=pump.getEastings();
            type=pump.getType();
            model=pump.getModel();
            pumpSize=pump.getPumpSize();
            remarks=pump.getRemarks();
            runningBalance=pump.getRunningBalance();
            comiteeContracts=pump.getComiteeContracts();
            drillingCompany=pump.getDrillingCompany();
            drillingYear=pump.getDrillingYear();
            boreholeDepth=pump.getBoreholeDepth();
            boreholeDiameter=pump.getBoreholeDiameter();
            testedYield=pump.getTestedYield();
            inlet=pump.getInlet();
            inletYieldTest=pump.getInletYieldTest();
            waterQuality=pump.getWaterQuality();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Set<String> getAdjacentPumps() {
            return adjacentPumps;
        }

        public void setAdjacentPumps(Set<String> adjacentPumps) {
            this.adjacentPumps = adjacentPumps;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTerritory() {
            return territory;
        }

        public void setTerritory(String territory) {
            this.territory = territory;
        }

        public String getBuiltBy() {
            return builtBy;
        }

        public void setBuiltBy(String builtBy) {
            this.builtBy = builtBy;
        }

        public String getMaintainedBy() {
            return maintainedBy;
        }

        public void setMaintainedBy(String maintainedBy) {
            this.maintainedBy = maintainedBy;
        }

        public boolean isUnderPMU() {
            return underPMU;
        }

        public void setUnderPMU(boolean underPMU) {
            this.underPMU = underPMU;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNorthings() {
            return northings;
        }

        public void setNorthings(String northings) {
            this.northings = northings;
        }

        public String getEastings() {
            return eastings;
        }

        public void setEastings(String eastings) {
            this.eastings = eastings;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPumpSize() {
            return pumpSize;
        }

        public void setPumpSize(String pumpSize) {
            this.pumpSize = pumpSize;
        }

        public List<String> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<String> remarks) {
            this.remarks = remarks;
        }

        public Double getRunningBalance() {
            return runningBalance;
        }

        public void setRunningBalance(Double runningBalance) {
            this.runningBalance = runningBalance;
        }

        public List<String> getComiteeContracts() {
            return comiteeContracts;
        }

        public void setComiteeContracts(List<String> comiteeContracts) {
            this.comiteeContracts = comiteeContracts;
        }

        public String getDrillingCompany() {
            return drillingCompany;
        }

        public void setDrillingCompany(String drillingCompany) {
            this.drillingCompany = drillingCompany;
        }

        public String getDrillingYear() {
            return drillingYear;
        }

        public void setDrillingYear(String drillingYear) {
            this.drillingYear = drillingYear;
        }

        public Double getBoreholeDepth() {
            return boreholeDepth;
        }

        public void setBoreholeDepth(Double boreholeDepth) {
            this.boreholeDepth = boreholeDepth;
        }

        public Double getBoreholeDiameter() {
            return boreholeDiameter;
        }

        public void setBoreholeDiameter(Double boreholeDiameter) {
            this.boreholeDiameter = boreholeDiameter;
        }

        public Double getStaticWaterLevel() {
            return staticWaterLevel;
        }

        public void setStaticWaterLevel(Double staticWaterLevel) {
            this.staticWaterLevel = staticWaterLevel;
        }

        public Double getDynamicWaterLevel() {
            return dynamicWaterLevel;
        }

        public void setDynamicWaterLevel(Double dynamicWaterLevel) {
            this.dynamicWaterLevel = dynamicWaterLevel;
        }

        public Double getTestedYield() {
            return testedYield;
        }

        public void setTestedYield(Double testedYield) {
            this.testedYield = testedYield;
        }

        public Double getInlet() {
            return inlet;
        }

        public void setInlet(Double inlet) {
            this.inlet = inlet;
        }

        public Double getInletYieldTest() {
            return inletYieldTest;
        }

        public void setInletYieldTest(Double inletYieldTest) {
            this.inletYieldTest = inletYieldTest;
        }

        public String getWaterQuality() {
            return waterQuality;
        }

        public void setWaterQuality(String waterQuality) {
            this.waterQuality = waterQuality;
        }

    public void update(PumpSchema pump) {
        if(pump.getName()!=null) name=pump.getName();
        if(pump.getTerritory()!=null) territory=pump.getTerritory();
        if(pump.getBuiltBy()!=null) builtBy=pump.getBuiltBy();
        if(pump.getMaintainedBy()!=null) maintainedBy=pump.getMaintainedBy();
        underPMU=pump.isUnderPMU();
        if(pump.getLocation()!=null) location=pump.getLocation();
        if(pump.getNorthings()!=null) northings=pump.getNorthings();
        if(pump.getEastings()!=null) eastings=pump.getEastings();
        if(pump.getType()!=null) type=pump.getType();
        if(pump.getModel()!=null) model=pump.getModel();
        if(pump.getPumpSize()!=null) pumpSize=pump.getPumpSize();
        if(pump.getRemarks()!=null) remarks=pump.getRemarks();
        if(pump.getRunningBalance()!=null) runningBalance=pump.getRunningBalance();
        if(pump.getComiteeContracts()!=null) comiteeContracts=pump.getComiteeContracts();
        if(pump.getDrillingCompany()!=null) drillingCompany=pump.getDrillingCompany();
        if(pump.getDrillingYear()!=null) drillingYear=pump.getDrillingYear();
        if(pump.getBoreholeDepth()!=null) boreholeDepth=pump.getBoreholeDepth();
        if(pump.getBoreholeDiameter()!=null) boreholeDiameter=pump.getBoreholeDiameter();
        if(pump.getTestedYield()!=null) testedYield=pump.getTestedYield();
        if(pump.getInlet()!=null) inlet=pump.getInlet();
        if(pump.getInletYieldTest()!=null) inletYieldTest=pump.getInletYieldTest();
        if(pump.getWaterQuality()!=null) waterQuality=pump.getWaterQuality();
    }
}
