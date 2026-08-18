package com.itvedant.groupmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estimate_master")
public class Estimate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estimated_id") private Integer estimatedId;
    @ManyToOne @JoinColumn(name = "chain_id", nullable = false) private Chain chain;
    @Column(name = "group_name", nullable = false, length = 50) private String groupName;
    @Column(name = "brand_name", nullable = false, length = 50) private String brandName;
    @Column(name = "zone_name", nullable = false, length = 50) private String zoneName;
    @Column(name = "service", nullable = false, length = 100) private String service;
    @Column(name = "qty", nullable = false) private Integer qty;
    @Column(name = "cost_per_unit", nullable = false) private Double costPerUnit;
    @Column(name = "total_cost", nullable = false) private Double totalCost;
    @Column(name = "delivery_date", nullable = false) private LocalDate deliveryDate;
    @Column(name = "delivery_details", length = 100) private String deliveryDetails;
    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;

    public Estimate() {}
    public Integer getEstimatedId(){return estimatedId;} public void setEstimatedId(Integer v){estimatedId=v;}
    public Chain getChain(){return chain;} public void setChain(Chain v){chain=v;}
    public String getGroupName(){return groupName;} public void setGroupName(String v){groupName=v;}
    public String getBrandName(){return brandName;} public void setBrandName(String v){brandName=v;}
    public String getZoneName(){return zoneName;} public void setZoneName(String v){zoneName=v;}
    public String getService(){return service;} public void setService(String v){service=v;}
    public Integer getQty(){return qty;} public void setQty(Integer v){qty=v;}
    public Double getCostPerUnit(){return costPerUnit;} public void setCostPerUnit(Double v){costPerUnit=v;}
    public Double getTotalCost(){return totalCost;} public void setTotalCost(Double v){totalCost=v;}
    public LocalDate getDeliveryDate(){return deliveryDate;} public void setDeliveryDate(LocalDate v){deliveryDate=v;}
    public String getDeliveryDetails(){return deliveryDetails;} public void setDeliveryDetails(String v){deliveryDetails=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
    @PrePersist public void onCreate(){createdAt=LocalDateTime.now();updatedAt=LocalDateTime.now();}
    @PreUpdate public void onUpdate(){updatedAt=LocalDateTime.now();}
}
