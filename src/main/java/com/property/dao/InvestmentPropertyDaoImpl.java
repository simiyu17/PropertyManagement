/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.property.dao;

import com.property.commondao.GenericDaoImpl;
import com.property.model.InvestmentProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author simiyu
 */

@Repository
@Transactional
public class InvestmentPropertyDaoImpl extends GenericDaoImpl<InvestmentProperty, String> implements InvestmentPropertyDao {

    @Override
    public void save(InvestmentProperty property) throws Exception {
        saveBean(property);
    }

    @Override
    public InvestmentProperty findById(String id) {
        return findBeanById(id);
    }

    @Override
    public List<InvestmentProperty> getInvestmentProperties(String codeString, String nameString, Boolean approved, LocalDate contructDateFrom, LocalDate contructDateTo) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<InvestmentProperty> criteria = builder.createQuery(InvestmentProperty.class);
        Root<InvestmentProperty> root = criteria.from(InvestmentProperty.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!Objects.isNull(codeString)) {
            predicates.add(builder.equal(root.get("code"), codeString));
        }
        if (!Objects.isNull(nameString)) {
            predicates.add(builder.like(root.get("name"), "%" + nameString + "%"));
        }
        if (!Objects.isNull(approved)) {
            predicates.add(builder.equal(root.get("approved"), approved));
        }
        if (!Objects.isNull(contructDateFrom) && !Objects.isNull(contructDateTo)) {
            predicates.add(builder.between(root.get("constructionDate"), contructDateFrom, contructDateTo));
        }
       
        if (predicates.size() > 0) {
            criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        return findByCriteria(-1, -1, criteria.select(root));
    }

    @Override
    public void delete(String id) throws Exception {
        deleteBeanById(id);
    }
    
}
