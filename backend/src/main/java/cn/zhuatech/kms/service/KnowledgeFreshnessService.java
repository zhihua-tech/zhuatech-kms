/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.kms.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.ArrayList; import java.util.List;
@Service public class KnowledgeFreshnessService {
    public Result evaluate(Request r){
        int votes=r.helpfulVotes()+r.unhelpfulVotes(); double helpfulness=votes==0?0:r.helpfulVotes()*100.0/votes;
        double reviewScore=Math.max(0,100-r.lastReviewDays()*100.0/365); double usageScore=Math.min(100,r.views90Days()/5.0);
        double score=reviewScore*.45+helpfulness*.35+usageScore*.20-(r.ownerActive()?0:20); score=Math.max(0,Math.min(100,score));
        String decision=!r.ownerActive()&&r.views90Days()<10?"RETIRE":r.lastReviewDays()>180||helpfulness<70?"REVIEW":r.views90Days()>500&&helpfulness>=90?"PROMOTE":"HEALTHY";
        List<String> actions=new ArrayList<>(); if(r.lastReviewDays()>180)actions.add("安排领域专家重新审核内容"); if(helpfulness<70)actions.add("分析低评价反馈并修订解决步骤"); if(r.linkedIncidentCount()>10)actions.add("结合关联事件补充故障场景"); if(!r.ownerActive())actions.add("重新指定有效知识责任人");
        return new Result(round(helpfulness),round(score),decision,actions);
    }
    private double round(double v){return Math.round(v*100)/100.0;}
    public record Request(@NotBlank String articleId,@Min(0) int ageDays,@Min(0) int lastReviewDays,@Min(0) int views90Days,
        @Min(0) int helpfulVotes,@Min(0) int unhelpfulVotes,@Min(0) int linkedIncidentCount,boolean ownerActive){}
    public record Result(double helpfulnessRate,double freshnessScore,String decision,List<String> actions){}
}

