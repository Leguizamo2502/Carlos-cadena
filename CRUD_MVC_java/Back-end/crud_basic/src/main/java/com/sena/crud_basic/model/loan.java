package com.sena.crud_basic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "loan")
public class loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan", length = 10, nullable = false)
    private int id_loan;

    @Column(name = "loan_date", nullable = false, length = 10)
    private LocalDate loan_date;

    @Column(name = "return_date", nullable = false, length = 10)
    private LocalDate return_date;

    @Column(name = "status", nullable = false, length = 20)
    private String status; 

    

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private users users;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<loan_detail> loan_detail = new ArrayList<>();

    public loan() {
    }

    public loan(int id_loan, LocalDate loan_date, LocalDate return_date, String status,
            com.sena.crud_basic.model.users users, List<com.sena.crud_basic.model.loan_detail> loan_detail) {
        this.id_loan = id_loan;
        this.loan_date = loan_date;
        this.return_date = return_date;
        this.status = status;
        this.users = users;
        this.loan_detail = loan_detail;
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

    public users getUsers() {
        return users;
    }

    public void setUsers(users users) {
        this.users = users;
    }

    public List<loan_detail> getLoan_detail() {
        return loan_detail;
    }

    public void setLoan_detail(List<loan_detail> loan_detail) {
        this.loan_detail = loan_detail;
    }


    
}
