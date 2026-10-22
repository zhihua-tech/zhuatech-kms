/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.kms.workbench;
import org.springframework.stereotype.Service;
import java.math.*;
import java.util.*;
import static cn.zhuatech.kms.workbench.WorkbenchInput.*;
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
    /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Map<String,Object> execute(Map<String,Object> r){
        validate(r);

        keys(r,"asOf","revisions");java.time.LocalDate asOf;try{asOf=java.time.LocalDate.parse(text(r,"asOf",10));}catch(java.time.DateTimeException e){throw new IllegalArgumentException("asOf日期无效");}
        Map<String,Map<Integer,String>> hashes=new TreeMap<>();Map<String,List<Map<String,Object>>> docs=new TreeMap<>();Set<String> conflicts=new TreeSet<>(), conflictDocs=new HashSet<>();int duplicate=0;
        for(var row:rows(r,"revisions",1,200)){keys(row,"documentId","version","content","published","validUntil");String id=id(row,"documentId"),content=text(row,"content",16000),until=text(row,"validUntil",10);int version=integer(row,"version",1,1000000);flag(row,"published");try{java.time.LocalDate.parse(until);}catch(java.time.DateTimeException e){throw new IllegalArgumentException("validUntil日期无效");}
            String fingerprint;try{fingerprint=HexFormat.of().formatHex(java.security.MessageDigest.getInstance("SHA-256").digest((content+"\u0000"+row.get("published")+"\u0000"+until).getBytes(java.nio.charset.StandardCharsets.UTF_8)));}catch(java.security.NoSuchAlgorithmException e){throw new IllegalStateException(e);}
            String previous=hashes.computeIfAbsent(id,x->new TreeMap<>()).putIfAbsent(version,fingerprint);if(previous!=null){if(previous.equals(fingerprint))duplicate++;else {conflicts.add(id+":"+version);conflictDocs.add(id); }}docs.computeIfAbsent(id,x->new ArrayList<>()).add(row);
        }
        List<Map<String,Object>> latest=new ArrayList<>();List<String> unavailable=new ArrayList<>();
        for(var e:docs.entrySet()){if(conflictDocs.contains(e.getKey())){unavailable.add(e.getKey());continue;}var match=e.getValue().stream().filter(x->flag(x,"published")&&!java.time.LocalDate.parse((String)x.get("validUntil")).isBefore(asOf)).max(Comparator.comparingInt(x->integer(x,"version",1,1000000)));if(match.isPresent())latest.add(Map.of("documentId",e.getKey(),"version",match.get().get("version")));else unavailable.add(e.getKey());}
        return Map.of("valid",conflicts.isEmpty(),"conflicts",List.copyOf(conflicts),"duplicateRevisions",duplicate,"latestPublished",latest,"unavailableDocuments",unavailable,"writesKnowledgeBase",false);

    }
}
