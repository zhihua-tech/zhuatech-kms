/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="kms_review_task") public class ReviewTask extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String reviewTaskNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private KnowledgeArticle knowledgeArticle;
    @Column(nullable=false,length=30) private String reviewTaskType; @Column(nullable=false) private int reviewTaskQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected ReviewTask(){} public ReviewTask(String reviewTaskNo,KnowledgeArticle knowledgeArticle,String reviewTaskType,int reviewTaskQty,int defectQty,Result result,String inspector){this.reviewTaskNo=reviewTaskNo;this.knowledgeArticle=knowledgeArticle;this.reviewTaskType=reviewTaskType;this.reviewTaskQty=reviewTaskQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getReviewTaskNo(){return reviewTaskNo;} public KnowledgeArticle getKnowledgeArticle(){return knowledgeArticle;} public String getReviewTaskType(){return reviewTaskType;} public int getReviewTaskQty(){return reviewTaskQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
