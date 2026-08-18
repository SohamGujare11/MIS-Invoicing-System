package com.codeb.ims.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "groups", uniqueConstraints = @UniqueConstraint(columnNames = "group_name"))
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id") private Long groupId;
    @Column(name = "group_name", nullable = false, unique = true) private String groupName;
    @Column(name = "is_active", nullable = false) private Boolean isActive = true;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;
    @PrePersist protected void onCreate(){ LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; if(isActive==null)isActive=true; }
    @PreUpdate protected void onUpdate(){ updatedAt=LocalDateTime.now(); }
    public Long getGroupId(){return groupId;} public void setGroupId(Long v){groupId=v;}
    public String getGroupName(){return groupName;} public void setGroupName(String v){groupName=v;}
    public Boolean getIsActive(){return isActive;} public void setIsActive(Boolean v){isActive=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}
