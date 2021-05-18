/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.property.controller;

import com.property.dao.InvestmentPropertyDaoImpl;
import com.property.model.InvestmentProperty;
import com.property.model.Response;
import com.property.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author simiyu
 */
@RequestMapping("/properties")
@RestController
@Api(value = "Properties", description = "Manage Properties")
public class InvestmentPropertyController {

    @Autowired
    private InvestmentPropertyDaoImpl propertyDao;

    // -------------------Retrieve All Investment Properties ------------------------------------------
    @ApiOperation(value = "View a list of available Properties", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    }
    )
    @GetMapping("/")
    public List<InvestmentProperty> listAll(@RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "pName", required = false) String pName, @RequestParam(name = "approved", required = false) Boolean approved,
            @RequestParam(name = "constructionFromDate", required = false) LocalDate constructionFromDate, @RequestParam(name = "constructionToDate", required = false) LocalDate constructionToDate) {
        return propertyDao.getInvestmentProperties(code, pName, approved, constructionToDate, constructionToDate);
    }

    // -------------------Retrieve Single InvestmentProperty------------------------------------------
    @ApiOperation(value = "Retrieve My Business", response = InvestmentProperty.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved Property"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInvestmentProperty(@PathVariable("id") String id) {
        InvestmentProperty invProperty = propertyDao.findById(id);
        if (Objects.isNull(invProperty)) {
            return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.RESOURCE_WITH_ID_NOT_FOUND + id),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<InvestmentProperty>(invProperty, HttpStatus.OK);
    }

    // -------------------Create a InvestmentProperty-------------------------------------------
    @ApiOperation(value = "Create a Property details")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully Created list"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),}
    )
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createInvestmentProperty(@RequestBody InvestmentProperty invProperty, UriComponentsBuilder ucBuilder)
            throws Exception {

        try {
            invProperty.setApproved(Boolean.FALSE);
            propertyDao.save(invProperty);
            return new ResponseEntity<Response>(new Response(true, MessageConstant.EndpointMessage.SAVE_SUCCESS),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.ERROR_OCCURED + e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // ------------------- Update a InvestmentProperty -------------------------------
    @ApiOperation(value = "Update a Property details with given ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Updated Property"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInvestmentProperty(@PathVariable("id") String id, @RequestBody InvestmentProperty invProperty) throws Exception {
        try {
            InvestmentProperty currentInvestmentProperty = propertyDao.findById(id);
            if (Objects.isNull(currentInvestmentProperty)) {
                return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.RESOURCE_WITH_ID_NOT_FOUND + id), HttpStatus.NOT_FOUND);
            }
            currentInvestmentProperty.setArea(!Objects.isNull(invProperty.getArea()) ? invProperty.getArea() : currentInvestmentProperty.getArea());
            currentInvestmentProperty.setCode(!Objects.isNull(invProperty.getCode()) ? invProperty.getCode() : currentInvestmentProperty.getCode());
            currentInvestmentProperty.setConstructionDate(!Objects.isNull(invProperty.getConstructionDate()) ? invProperty.getConstructionDate() : currentInvestmentProperty.getConstructionDate());
            currentInvestmentProperty.setDesc(!Objects.isNull(invProperty.getDesc()) ? invProperty.getDesc() : currentInvestmentProperty.getDesc());
            currentInvestmentProperty.setLeaseStartDate(!Objects.isNull(invProperty.getLeaseStartDate()) ? invProperty.getLeaseStartDate() : currentInvestmentProperty.getLeaseStartDate());
            currentInvestmentProperty.setLeaseEndDate(!Objects.isNull(invProperty.getLeaseEndDate()) ? invProperty.getLeaseEndDate() : currentInvestmentProperty.getLeaseEndDate());
            currentInvestmentProperty.setLocation(!Objects.isNull(invProperty.getLocation()) ? invProperty.getLocation() : currentInvestmentProperty.getLocation());
            currentInvestmentProperty.setName(!Objects.isNull(invProperty.getName()) ? invProperty.getName() : currentInvestmentProperty.getName());
            currentInvestmentProperty.setOriginalCost(!Objects.isNull(invProperty.getOriginalCost()) ? invProperty.getOriginalCost() : currentInvestmentProperty.getOriginalCost());
            currentInvestmentProperty.setUnitCost(!Objects.isNull(invProperty.getUnitCost()) ? invProperty.getUnitCost() : currentInvestmentProperty.getUnitCost());
            currentInvestmentProperty.setLeased(!Objects.isNull(invProperty.getLeased()) ? invProperty.getLeased() : currentInvestmentProperty.getLeased());
            currentInvestmentProperty.setApproved(currentInvestmentProperty.isApproved());

            propertyDao.save(currentInvestmentProperty);
            return new ResponseEntity<Response>(new Response(true, MessageConstant.EndpointMessage.UPDATE_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.ERROR_OCCURED + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    // ------------------- Approve an InvestmentProperty -------------------------------
    @ApiOperation(value = "Approve a property with given ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Approved Property"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "/approve/{id}")
    public ResponseEntity<?> approveInvestmentProperty(@PathVariable("id") String id) throws Exception {
        try {
            InvestmentProperty currentInvestmentProperty = propertyDao.findById(id);
            if (Objects.isNull(currentInvestmentProperty)) {
                return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.RESOURCE_WITH_ID_NOT_FOUND + id), HttpStatus.NOT_FOUND);
            }
            currentInvestmentProperty.setApproved(Boolean.TRUE);

            propertyDao.save(currentInvestmentProperty);
            return new ResponseEntity<Response>(new Response(true, MessageConstant.EndpointMessage.UPDATE_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.ERROR_OCCURED + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    // ------------------- Delete a InvestmentProperty-----------------------------------------
    @ApiOperation(value = "Delete a property details with given ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Deleted Property"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteInvestmentProperty(@PathVariable("id") String id) throws Exception {

        try {
            InvestmentProperty invProperty = propertyDao.findById(id);
            if (Objects.isNull(invProperty)) {
                return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.RESOURCE_WITH_ID_NOT_FOUND + id),
                        HttpStatus.NOT_FOUND);
            }
            propertyDao.delete(invProperty.getId());
            return new ResponseEntity<Response>(new Response(true, MessageConstant.EndpointMessage.DELETE_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, MessageConstant.EndpointMessage.ERROR_OCCURED + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
