/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms;
import cn.zhuatech.kms.service.KnowledgeGapService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class KnowledgeGapServiceTests {private final KnowledgeGapService service=new KnowledgeGapService();@Test void createsContentForLargeGap(){var r=service.evaluate(new KnowledgeGapService.Request(100,60,10,0,3));assertEquals("CREATE_CONTENT",r.status());}@Test void monitorsWellCoveredTopic(){var r=service.evaluate(new KnowledgeGapService.Request(1000,10,0,2,5));assertEquals("MONITOR",r.status());}}
