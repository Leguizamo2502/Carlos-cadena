package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.DTO.responseLoanDto;
import com.sena.crud_basic.model.loan;

@Repository
public interface ILoan extends JpaRepository<loan, Integer> {
    // @Query("SELECT l FROM loan l WHERE l.id_loan = ?1")
    // loan findById(int id_loan);
    @Query(value = 
        """
        SELECT 
            loan.id_loan AS id_loan, 
            loan.loan_date AS loan_date,
            loan.status AS status,
            loan.return_date AS return_date,
            CONCAT(users.first_name, ' ', users.last_name) AS fullName, 
            book.title AS bookTitle
        FROM loan
        JOIN users ON loan.id_user = users.id_user
        JOIN loan_detail ON loan.id_loan = loan_detail.id_loan
        JOIN book ON loan_detail.id_book = book.id_book
        ORDER BY loan.id_loan DESC
        """, 
        nativeQuery = true)
List<responseLoanDto> findAllJoin();


}
