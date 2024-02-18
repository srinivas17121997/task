package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mf_portfolio_value", schema = "public")
public class MFPortfolioValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portfolio_value", nullable = false)
    private Double portfolioValue;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;


    public MFPortfolioValue() {
        // Default constructor
    }

    public MFPortfolioValue(Double portfolioValue, Date time) {
        this.portfolioValue = portfolioValue;
        this.time = time;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(Double portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
