package com.gergo.alkalmazas.model;

import javax.persistence.*;

@Entity
@Table (name="kamera")
public class Kamera {

    public Kamera() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "haz_id")
    private Integer hazId;

    @Column(name = "optika_id")
    private Integer optikaId;

    @Column(name = "filter_id")
    private Integer filterId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHazId() {
        return hazId;
    }

    public void setHazId(Integer hazId) {
        this.hazId = hazId;
    }

    public Integer getOptikaId() {
        return optikaId;
    }

    public void setOptikaId(Integer optikaId) {
        this.optikaId = optikaId;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }
}
