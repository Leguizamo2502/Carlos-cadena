package com.sena.crud_basic.model;



import jakarta.persistence.*;

@Entity(name = "loan_detail")
public class loan_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan_detail", length = 10, nullable = false)
    private Integer id_loan_detail;

    @Column(name = "quantity", nullable = false, length = 10)
    private Integer quantity;

    public loan_detail() {
    }

    public loan_detail(Integer id_loan_detail, Integer quantity) {
        this.id_loan_detail = id_loan_detail;
        this.quantity = quantity;
    }

    public Integer getId_loan_detail() {
        return id_loan_detail;
    }

    public void setId_loan_detail(Integer id_loan_detail) {
        this.id_loan_detail = id_loan_detail;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // @ManyToOne
    // @JoinColumn(name = "id_loan", nullable = false)
    // private loan loan;

    // @ManyToOne
    // @JoinColumn(name = "id_book", nullable = false)
    // private book book;

}
