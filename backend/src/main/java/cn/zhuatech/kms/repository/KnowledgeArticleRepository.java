/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.repository; import cn.zhuatech.kms.model.KnowledgeArticle; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface KnowledgeArticleRepository extends JpaRepository<KnowledgeArticle,Long>{List<KnowledgeArticle> findAllByOrderByDueDateAsc();List<KnowledgeArticle> findByKnowledgeSpaceCodeOrderByDueDateAsc(String code);long countByStatus(KnowledgeArticle.Status status);}
