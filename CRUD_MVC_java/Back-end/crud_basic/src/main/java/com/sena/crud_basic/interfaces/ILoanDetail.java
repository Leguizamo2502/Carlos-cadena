package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.loan_detail;

@Repository
public interface ILoanDetail extends JpaRepository<loan_detail,Integer>{

}
