package com.itvedant.groupmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="invoice_master", uniqueConstraints=@UniqueConstraint(name="uk_invoice_no", columnNames="invoice_no"))
public class Invoice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id") private Integer id;
    @Column(name="invoice_no", nullable=false, unique=true) private Integer invoiceNo;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="estimated_id", nullable=false) @JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) private Estimate estimate;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="chain_id", nullable=false) @JsonIgnoreProperties({"hibernateLazyInitializer","handler","group"}) private Chain chain;
    @Column(name="service_details", nullable=false,length=100) private String serviceDetails;
    @Column(name="qty",nullable=false) private Integer qty;
    @Column(name="cost_per_qty",nullable=false) private Double costPerQty;
    @Column(name="amount_payable",nullable=false) private Double amountPayable;
    @Column(name="amount_paid",nullable=false) private Double amountPaid;
    @Column(name="balance",nullable=false) private Double balance;
    @Column(name="date_of_payment") private LocalDateTime dateOfPayment;
    @Column(name="date_of_service",nullable=false) private LocalDate dateOfService;
    @Column(name="delivery_details",length=100) private String deliveryDetails;
    @Column(name="email_id",length=100) private String emailId;
    @Column(name="created_at") private LocalDateTime createdAt;
    @Column(name="updated_at") private LocalDateTime updatedAt;
    public Invoice() {}
    public Integer getId(){return id;} public void setId(Integer v){id=v;}
    public Integer getInvoiceNo(){return invoiceNo;} public void setInvoiceNo(Integer v){invoiceNo=v;}
    public Estimate getEstimate(){return estimate;} public void setEstimate(Estimate v){estimate=v;}
    public Chain getChain(){return chain;} public void setChain(Chain v){chain=v;}
    public String getServiceDetails(){return serviceDetails;} public void setServiceDetails(String v){serviceDetails=v;}
    public Integer getQty(){return qty;} public void setQty(Integer v){qty=v;}
    public Double getCostPerQty(){return costPerQty;} public void setCostPerQty(Double v){costPerQty=v;}
    public Double getAmountPayable(){return amountPayable;} public void setAmountPayable(Double v){amountPayable=v;}
    public Double getAmountPaid(){return amountPaid;} public void setAmountPaid(Double v){amountPaid=v;}
    public Double getBalance(){return balance;} public void setBalance(Double v){balance=v;}
    public LocalDateTime getDateOfPayment(){return dateOfPayment;} public void setDateOfPayment(LocalDateTime v){dateOfPayment=v;}
    public LocalDate getDateOfService(){return dateOfService;} public void setDateOfService(LocalDate v){dateOfService=v;}
    public String getDeliveryDetails(){return deliveryDetails;} public void setDeliveryDetails(String v){deliveryDetails=v;}
    public String getEmailId(){return emailId;} public void setEmailId(String v){emailId=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
    @PrePersist public void onCreate(){LocalDateTime now=LocalDateTime.now();createdAt=now;updatedAt=now;}
    @PreUpdate public void onUpdate(){updatedAt=LocalDateTime.now();}
}
