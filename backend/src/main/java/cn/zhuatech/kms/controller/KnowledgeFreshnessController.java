/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.controller;
import cn.zhuatech.kms.common.ApiResponse; import cn.zhuatech.kms.service.KnowledgeFreshnessService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") public class KnowledgeFreshnessController {private final KnowledgeFreshnessService service; public KnowledgeFreshnessController(KnowledgeFreshnessService service){this.service=service;} @PostMapping("/knowledge-freshness") public ApiResponse<KnowledgeFreshnessService.Result> evaluate(@Valid @RequestBody KnowledgeFreshnessService.Request request){return ApiResponse.ok(service.evaluate(request));}}

