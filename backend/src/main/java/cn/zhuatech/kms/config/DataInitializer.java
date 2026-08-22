/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.config;

import cn.zhuatech.kms.model.*;
import cn.zhuatech.kms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(KnowledgeSpaceRepository knowledgeSpaces, KnowledgeArticleRepository orders,
                           ExpertRepository experts, ReviewTaskRepository reviewTasks,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (knowledgeSpaces.count() > 0) return;
            KnowledgeSpace chemistry = knowledgeSpaces.save(new KnowledgeSpace("KS-CHEM", "产品知识空间", "知识治理中心", 180));
            KnowledgeSpace micro = knowledgeSpaces.save(new KnowledgeSpace("KS-MICRO", "交付知识空间", "研发中心", 120));
            KnowledgeSpace material = knowledgeSpaces.save(new KnowledgeSpace("KS-MAT", "技术知识空间", "工程中心", 96));

            KnowledgeArticle t1 = orders.save(new KnowledgeArticle("KA-260801-018", "GB-T-228", "网关安装调试指南", material, 24, 16, 1, LocalDate.now().plusDays(1), KnowledgeArticle.Status.RUNNING, "S260801-A"));
            KnowledgeArticle t2 = orders.save(new KnowledgeArticle("KA-260801-021", "HPLC-042", "客户上线验收手册", chemistry, 18, 8, 0, LocalDate.now().plusDays(1), KnowledgeArticle.Status.RUNNING, "S260801-C"));
            KnowledgeArticle t3 = orders.save(new KnowledgeArticle("KA-260802-006", "ISO-4833", "微服务故障排查指南", micro, 12, 0, 0, LocalDate.now().plusDays(3), KnowledgeArticle.Status.RELEASED, "S260802-B"));
            KnowledgeArticle t4 = orders.save(new KnowledgeArticle("KA-260731-015", "ICP-017", "项目复盘方法模板", chemistry, 20, 20, 1, LocalDate.now(), KnowledgeArticle.Status.COMPLETED, "S260731-D"));

            experts.saveAll(List.of(
                new Expert("EXP-HPLC-03", "工业互联专家 03", chemistry, Expert.Status.RUNNING, 88),
                new Expert("EXP-ICP-02", "项目治理专家", chemistry, Expert.Status.IDLE, 76),
                new Expert("EXP-UTM-05", "交付实施专家", material, Expert.Status.RUNNING, 91),
                new Expert("EXP-INC-08", "云平台专家 08", micro, Expert.Status.ALARM, 62)
            ));
            reviewTasks.saveAll(List.of(
                new ReviewTask("REV-260801-032", t1, "留样评审", 6, 0, ReviewTask.Result.PASSED, "周妍"),
                new ReviewTask("REV-260801-011", t2, "前处理评审", 3, 0, ReviewTask.Result.PASSED, "陆承"),
                new ReviewTask("REV-260801-018", t4, "结果评审", 5, 1, ReviewTask.Result.FAILED, "周妍"),
                new ReviewTask("REV-260802-003", t3, "收样确认", 4, 0, ReviewTask.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "陆承", UserAccount.Role.CONTRIBUTOR, "KS-CHEM"),
                new UserAccount("planner", demo, "周妍", UserAccount.Role.KNOWLEDGE_MANAGER, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
