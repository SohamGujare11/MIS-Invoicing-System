package com.itvedant.groupmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brand_master")
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id") private Integer brandId;
    @Column(name = "brand_name", nullable = false, length = 50) private String brandName;
    @ManyToOne @JoinColumn(name = "chain_id", nullable = false) private Chain chain;
    @Column(name = "is_active") private Boolean isActive = true;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at") private LocalDateTime updatedAt = LocalDateTime.now();
    public Brand() {}
    public Integer getBrandId(){return brandId;} public void setBrandId(Integer v){brandId=v;}
    public String getBrandName(){return brandName;} public void setBrandName(String v){brandName=v;}
    public Chain getChain(){return chain;} public void setChain(Chain v){chain=v;}
    public Boolean getIsActive(){return isActive;} public void setIsActive(Boolean v){isActive=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}
