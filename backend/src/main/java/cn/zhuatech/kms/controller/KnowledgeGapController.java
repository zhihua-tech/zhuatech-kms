/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.controller;
import cn.zhuatech.kms.common.ApiResponse;import cn.zhuatech.kms.service.KnowledgeGapService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/kms/insights/knowledge-gap") public class KnowledgeGapController {private final KnowledgeGapService service;/**
                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                   */
public KnowledgeGapController(KnowledgeGapService service){this.service=service;}/**
                                                                                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                    */
@PostMapping ApiResponse<KnowledgeGapService.Result> evaluate(@Valid @RequestBody KnowledgeGapService.Request request){return ApiResponse.ok(service.evaluate(request));}}
