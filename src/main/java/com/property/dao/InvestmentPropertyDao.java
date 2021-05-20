/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.property.dao;

import com.property.model.InvestmentProperty;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author simiyu
 */
public interface InvestmentPropertyDao {
    
    InvestmentProperty save(InvestmentProperty property)throws Exception;
    
    InvestmentProperty findById(String id);
    
    List<InvestmentProperty> getInvestmentProperties(String codeString, String nameString, Boolean approved, LocalDate contructDateFrom, LocalDate contructDateTo);
    
    List<InvestmentProperty> getAllInvestmentProperties();
    
    void delete(String id)throws Exception;
    
}
