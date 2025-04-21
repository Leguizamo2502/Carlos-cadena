package com.sena.crud_basic.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "loan_detail")
public class loan_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan_detail", length = 10, nullable = false)
    private int id_loan_detail;

    @Column(name = "quantity", nullable = false, length = 10)
    private int quantity;

    

    // @ManyToOne
    // @JoinColumn(name = "id_loan", nullable = false)
    // private loan loan;

    // @ManyToOne
    // @JoinColumn(name = "id_book", nullable = false)
    // private book book;
    @ManyToOne
    @JoinColumn(name = "id_loan", referencedColumnName = "id_loan")
    // @JsonIgnoreProperties({"loan_detail", "users"})
    private loan loan;
    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    // @JsonIgnoreProperties({"loan_detail","book_publisher","description","book_author", "book_category", "loans"})
    private book book;
    public loan_detail() {
    }
    public loan_detail(int id_loan_detail, int quantity, com.sena.crud_basic.model.loan loan,
            com.sena.crud_basic.model.book book) {
        this.id_loan_detail = id_loan_detail;
        this.quantity = quantity;
        this.loan = loan;
        this.book = book;
    }
    public int getId_loan_detail() {
        return id_loan_detail;
    }
    public void setId_loan_detail(int id_loan_detail) {
        this.id_loan_detail = id_loan_detail;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public loan getLoan() {
        return loan;
    }
    public void setLoan(loan loan) {
        this.loan = loan;
    }
    public book getBook() {
        return book;
    }
    public void setBook(book book) {
        this.book = book;
    }

    

}
