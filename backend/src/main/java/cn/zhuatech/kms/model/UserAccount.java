/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.kms.model;
import jakarta.persistence.*;
@Entity @Table(name="kms_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, KNOWLEDGE_MANAGER, CONTRIBUTOR, QUALITY }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @Column(name="knowledge_space_code",length=32) private String knowledgeSpaceCode; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,String knowledgeSpaceCode){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.knowledgeSpaceCode=knowledgeSpaceCode;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public String getKnowledgeSpaceCode(){return knowledgeSpaceCode;} public boolean isEnabled(){return enabled;}
}
