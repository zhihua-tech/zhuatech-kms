/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.kms.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service public class KnowledgeGapService {
 public Result evaluate(Request r){double gap=r.unansweredSearches()*100.0/r.totalSearches();int score=(int)Math.round(Math.min(100,gap*.7+Math.min(30,r.repeatedSupportTickets()*3)+(r.availableExperts()==0?20:0)));String status=score>=60?"CREATE_CONTENT":score>=30?"ASSIGN_EXPERT":"MONITOR";List<String> actions=new ArrayList<>();if(gap>15)actions.add("围绕高频未命中搜索创建知识专题");if(r.repeatedSupportTickets()>5)actions.add("将重复工单转化为标准解决方案");if(r.availableExperts()==0)actions.add("指定领域专家负责内容验收");if(r.relatedArticles()>0)actions.add("合并或重构现有相关知识条目");if(actions.isEmpty())actions.add("知识覆盖稳定，持续监测搜索反馈");return new Result(Math.round(gap*10)/10.0,score,status,actions);}
 public record Request(@Min(1) int totalSearches,@Min(0) int unansweredSearches,@Min(0) int repeatedSupportTickets,@Min(0) int availableExperts,@Min(0) int relatedArticles){}
 public record Result(double unansweredRate,int priorityScore,String status,List<String> actions){}
}
