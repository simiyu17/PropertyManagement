/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.property.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author simiyu
 */
@Entity
@Table(name = "properties")
public class InvestmentProperty extends BaseEntity {

    @ApiModelProperty(notes = "The Property Unique Identifier")
    @Column(name = "code", unique = true)
    private String code;

    @ApiModelProperty(notes = "The name of the property")
    @Column(name = "property_name")
    private String name;

    @ApiModelProperty(notes = "A brief description on the property")
    @Column(name = "descr")
    private String desc;

    @ApiModelProperty(notes = "The total area of the property in square meters")
    @Column(name = "area")
    private BigDecimal area;

    @ApiModelProperty(notes = "The cost of creating single unit")
    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @ApiModelProperty(notes = "The property location")
    @Column(name = "location")
    private String location;

    @ApiModelProperty(notes = "The original cost of the property")
    @Column(name = "origin_cost")
    private BigDecimal originalCost;

    @ApiModelProperty(notes = "The property construction date")
    @Column(name = "date_of_construction")
    private LocalDate constructionDate;

    @ApiModelProperty(notes = "The lease start date")
    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;

    @ApiModelProperty(notes = "The lease end date")
    @Column(name = "lease_end_date")
    private LocalDate leaseEndDate;
    
    @ApiModelProperty(notes = "Whether property is approved or not")
    @Column(name = "approved")
    private Boolean approved;
    
    @ApiModelProperty(notes = "Whether property is leased or not")
    @Column(name = "leased")
    private Boolean leased;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the unitCost
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the originalCost
     */
    public BigDecimal getOriginalCost() {
        return originalCost;
    }

    /**
     * @param originalCost the originalCost to set
     */
    public void setOriginalCost(BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    /**
     * @return the constructionDate
     */
    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    /**
     * @param constructionDate the constructionDate to set
     */
    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }

    /**
     * @return the leaseStartDate
     */
    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    /**
     * @param leaseStartDate the leaseStartDate to set
     */
    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    /**
     * @return the leaseEndDate
     */
    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    /**
     * @param leaseEndDate the leaseEndDate to set
     */
    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the leased
     */
    public Boolean getLeased() {
        return leased;
    }

    /**
     * @param leased the leased to set
     */
    public void setLeased(Boolean leased) {
        this.leased = leased;
    }

}
