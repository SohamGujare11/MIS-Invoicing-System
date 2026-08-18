package com.itvedant.groupmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "zone_master")
public class Zone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id") private Integer zoneId;
    @Column(name = "zone_name", nullable = false, length = 100) private String zoneName;
    @ManyToOne @JoinColumn(name = "brand_id", nullable = false) private Brand brand;
    @Column(name = "is_active") private Boolean isActive = true;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at") private LocalDateTime updatedAt = LocalDateTime.now();
    public Zone() {}
    public Integer getZoneId(){return zoneId;} public void setZoneId(Integer v){zoneId=v;}
    public String getZoneName(){return zoneName;} public void setZoneName(String v){zoneName=v;}
    public Brand getBrand(){return brand;} public void setBrand(Brand v){brand=v;}
    public Boolean getIsActive(){return isActive;} public void setIsActive(Boolean v){isActive=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}
