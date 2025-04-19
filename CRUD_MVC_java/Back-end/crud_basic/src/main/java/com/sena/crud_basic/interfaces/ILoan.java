package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.loan;

@Repository
public interface ILoan extends JpaRepository<loan, Integer> {
    // @Query("SELECT l FROM loan l WHERE l.id_loan = ?1")
    // loan findById(int id_loan);

}
