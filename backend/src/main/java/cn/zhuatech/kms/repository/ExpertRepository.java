/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.kms.repository; import cn.zhuatech.kms.model.Expert; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ExpertRepository extends JpaRepository<Expert,Long>{List<Expert> findAllByOrderByCodeAsc();long countByStatus(Expert.Status status);}
