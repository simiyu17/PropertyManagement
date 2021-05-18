/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.property.util;

/**
 *
 * @author simiyu
 */
public interface MessageConstant {

    interface EndpointMessage {

        String SAVE_SUCCESS = "Saved Successfully";
        String UPDATE_SUCCESS = "Updated Successfully";
        String DELETE_SUCCESS = "Deleted Successfully";
        String MISSING_FIELD_VALUES = "Missing Some Mandatory Fields";
        String RESOURCE_NOT_FOUND = "No Resource Found";
        String RESOURCE_WITH_ID_NOT_FOUND = "No Resource Found With Id: ";
        String ERROR_OCCURED = "An Error Occured  ";
        String INVALID_VALUES = "Some Field Values Are Invalid";
        String INVALID_DATES = "Invalid Dates Selected";
        String NO_SELECTION = "No Resource Selected";
        String INVALID_CREDENTIAL = "Invalid Credentials";
    }

}
