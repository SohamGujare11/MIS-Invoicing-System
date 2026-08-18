package com.itvedant.groupmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chain_master")
public class Chain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chain_id") private Integer chainId;
    @Column(name = "chain_name", nullable = false, unique = true, length = 255) private String chainName;
    @Column(name = "company_name", nullable = false) private String companyName;
    @Column(name = "gstn_no", unique = true, nullable = false, length = 15) private String gstnNo;
    @ManyToOne @JoinColumn(name = "group_id", nullable = false) private Group group;
    @Column(name = "is_active") private Boolean isActive = true;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at") private LocalDateTime updatedAt = LocalDateTime.now();
    public Chain() {}
    public Integer getChainId(){return chainId;} public void setChainId(Integer v){chainId=v;}
    public String getChainName(){return chainName;} public void setChainName(String v){chainName=v;}
    public String getCompanyName(){return companyName;} public void setCompanyName(String v){companyName=v;}
    public String getGstnNo(){return gstnNo;} public void setGstnNo(String v){gstnNo=v;}
    public Group getGroup(){return group;} public void setGroup(Group v){group=v;}
    public Boolean getIsActive(){return isActive;} public void setIsActive(Boolean v){isActive=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}

class Group {}