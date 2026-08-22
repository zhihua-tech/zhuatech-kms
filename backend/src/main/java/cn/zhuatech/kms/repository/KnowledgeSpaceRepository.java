/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.repository; import cn.zhuatech.kms.model.KnowledgeSpace; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface KnowledgeSpaceRepository extends JpaRepository<KnowledgeSpace,Long>{Optional<KnowledgeSpace> findByCode(String code);}
