/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.repository; import cn.zhuatech.kms.model.KnowledgeArticle; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface KnowledgeArticleRepository extends JpaRepository<KnowledgeArticle,Long>{/**
                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                          */
List<KnowledgeArticle> findAllByOrderByDueDateAsc();/**
                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                              */
List<KnowledgeArticle> findByKnowledgeSpaceCodeOrderByDueDateAsc(String code);/**
                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                            */
long countByStatus(KnowledgeArticle.Status status);}
