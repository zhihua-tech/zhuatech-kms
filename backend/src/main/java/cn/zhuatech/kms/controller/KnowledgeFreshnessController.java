/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.controller;
import cn.zhuatech.kms.common.ApiResponse; import cn.zhuatech.kms.service.KnowledgeFreshnessService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin") public class KnowledgeFreshnessController {private final KnowledgeFreshnessService service; /**
                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                           */
public KnowledgeFreshnessController(KnowledgeFreshnessService service){this.service=service;} /**
                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                         */
@PostMapping("/knowledge-freshness") public ApiResponse<KnowledgeFreshnessService.Result> evaluate(@Valid @RequestBody KnowledgeFreshnessService.Request request){return ApiResponse.ok(service.evaluate(request));}}

