package com.sena.crud_basic.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity(name = "loan")
public class loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan", length = 10, nullable = false)
    private Integer id_loan;

    @Column(name = "loan_date", nullable = false, length = 10)
    private LocalDate loan_date;

    @Column(name = "return_date", nullable = false, length = 10)
    private LocalDate return_date;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // status: ['returned', 'pending', 'late']

    public loan() {
    }

    public loan(Integer id_loan, LocalDate loan_date, LocalDate return_date, String status) {
        this.id_loan = id_loan;
        this.loan_date = loan_date;
        this.return_date = return_date;
        this.status = status;
    }

    public Integer getId_loan() {
        return id_loan;
    }

    public void setId_loan(Integer id_loan) {
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

    // @ManyToOne
    // @JoinColumn(name = "id_user", nullable = false)
    // private user user;
}
