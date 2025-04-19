package com.sena.crud_basic.DTO;

import java.time.LocalDate;

public class requestRegisterLoanDto {
    private int id_loan;
    private LocalDate loan_date;
    private LocalDate return_date;
    private String status;
    private int id_user;
    public requestRegisterLoanDto() {
    }
    public requestRegisterLoanDto(int id_loan, LocalDate loan_date, LocalDate return_date, String status, int id_user) {
        this.id_loan = id_loan;
        this.loan_date = loan_date;
        this.return_date = return_date;
        this.status = status;
        this.id_user = id_user;
    }
    public int getId_loan() {
        return id_loan;
    }
    public void setId_loan(int id_loan) {
        this.id_loan = id_loan;
    }
    public LocalDate getLoan_date() {
        return loan_date;
    }
    public void setLoan_date(LocalDate loan_date) {
        this.loan_date = loan_date;
    }
    public LocalDate getReturn_date() {
        return return_date;
    }
    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    

    


}
