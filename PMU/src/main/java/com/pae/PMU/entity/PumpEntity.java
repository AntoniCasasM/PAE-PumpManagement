package com.pae.PMU.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


    @Entity
    @Table(name = "PumpEntity")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class PumpEntity implements Serializable {

        @Id
        private String id;
        @Column(length = 300)

        @ElementCollection
        private List<String> adjacentPumps;

        public PumpEntity() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getAdjacentPumps() {
            return adjacentPumps;
        }

        public void setAdjacentPumps(List<String> adjacentPumps) {
            this.adjacentPumps = adjacentPumps;
        }
    }
