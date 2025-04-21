package com.sena.crud_basic.DTO;

public class requestRegisterLoanDetailDto {
    private int id_loan_detail;
    // private int quantity;
    private int id_loan;
    private int id_book;
    public requestRegisterLoanDetailDto() {
    }
    public requestRegisterLoanDetailDto(int id_loan_detail, int id_loan, int id_book) {
        this.id_loan_detail = id_loan_detail;
        this.id_loan = id_loan;
        this.id_book = id_book;
    }
    public int getId_loan_detail() {
        return id_loan_detail;
    }
    public void setId_loan_detail(int id_loan_detail) {
        this.id_loan_detail = id_loan_detail;
    }
    public int getId_loan() {
        return id_loan;
    }
    public void setId_loan(int id_loan) {
        this.id_loan = id_loan;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    

    

}
