package com.dcy.springbootrabbitmqconsumer.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;
import com.dcy.springbootrabbitmqconsumer.search.dao.KnowledgeEsMapper;
import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;
import com.dcy.springbootrabbitmqconsumer.search.service.KnowledgeService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName KnowledgeServiceImpl
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 12:49
 * @Version 1.0
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {


    @Autowired
      private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private KnowledgeEsMapper knowledgeEsMapper;


/**
 * @Author Mr.Dong
 * @Description //创建knowledge索引库
 * @Date 12:55 2020/10/26
 * @Param []
 * @return void
 **/
    @Override
    public void createEsIndexAndMapping() {
//        创建索引库
        elasticsearchTemplate.createIndex(KnowledgeEs.class);
//        创建mapping映射配置
       elasticsearchTemplate.putMapping(KnowledgeEs.class);
    }
/**
 * @Author Mr.Dong
 * @Description //新增
 * @Date 12:57 2020/10/26
 * @Param []
 * @return void
 **/
    public void addKnowledgeToEs(KnowledgeEs knowledge){
        knowledgeEsMapper.save(knowledge);
    }
    /**
     * @Author Mr.Dong
     * @Description //批量新增（数据全量导入）
     * @Date 12:57 2020/10/26
     * @Param []
     * @return void
     **/
    public void addKmListToEs(List<KnowledgeEs> kmList){
        knowledgeEsMapper.saveAll(kmList);
    }

    @Override
    public Map<String, Object> simpleSearch(Map<String, String> paramMap) {
         if (paramMap==null){
          throw new RuntimeException("查询条件不可以为空");
         }
        //获取查询的关键字
        String keyword = paramMap.get("keyword");
         //获取version版本信息
        String version=paramMap.get("version");
         /*
          * @Author Mr.Dong
          * @Description //初始化返回对象
          * @Date 21:50 2020/10/26
          * @Param [paramMap]
          * @return java.util.Map<java.lang.String,java.lang.Object>
          **/
         Map<String,Object> resultMap =new HashMap<>();

         //创建最顶级的查询对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder=new NativeSearchQueryBuilder();
        //创建复合查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();


        //1.根据关键字查询
        if (!StringUtils.isEmpty(keyword)){
            //根据关键字模糊查询，创建对象，切分词，目录查询; 参数一：要模糊查询的Es的field域
            MatchQueryBuilder mqb = QueryBuilders.matchQuery("knowledgename", keyword);
            boolQueryBuilder.must(mqb.operator(Operator.AND));//相当于and；并且的意思 切出来的分词之间是并且的关系
//            boolQueryBuilder.mustNot(mqb);//相当于not ,非
//            boolQueryBuilder.should(mqb);//相当于or ,或者
        }

         //2.过滤查询ES索引库数据 根据version Field   .keyword :类型转换，
        if (!StringUtils.isEmpty(version)){
            TermQueryBuilder vers = QueryBuilders.termQuery("version"+".keyword", version);//过滤查询，不能切分词keyword类型的
            boolQueryBuilder.filter(vers);

        }
            //3. 范围查询; gte:>=   lte:<=
            RangeQueryBuilder id1 = QueryBuilders.rangeQuery("id").gte(100).lte(102);
            boolQueryBuilder.filter(id1);

            //4.根据名称高亮显示
        HighlightBuilder.Field field = new HighlightBuilder.Field("knowledgename").preTags("<span style=\"color=red\">").postTags("</span>");
        nativeSearchQueryBuilder.withHighlightFields(field);

        //5.根据id排序，DESC：升序，ASC：降序
        FieldSortBuilder order = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        nativeSearchQueryBuilder.withSort(order);

        //6.分页
        Integer pageNumber=1;
        Integer pageSize=20;
        //在Es中起始页默认是从0开始的。所以要减一。
        PageRequest of = PageRequest.of(pageNumber - 1, pageSize);
        nativeSearchQueryBuilder.withPageable(of);

        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);

        /*
         *获取查询结果及高亮结果
         */
        AggregatedPage<KnowledgeEs> knowledgeEss = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), KnowledgeEs.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //保存结果集
                List<KnowledgeEs> kmLists=new ArrayList<>();
                //获取结果集
                SearchHits hits = searchResponse.getHits();
                if (hits!=null){
                    for (SearchHit hit : hits) {
                        //获取普通结果集对象
                        String kmStr = hit.getSourceAsString();
                        //使用JSON转换为对象
                        KnowledgeEs knowledgeEs = JSON.parseObject(kmStr, KnowledgeEs.class);
                        //获取高亮字段
                        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                        if (highlightFields!=null && highlightFields.size()>0){
                            knowledgeEs.setKnowledgename(highlightFields.get("knowledgename").fragments()[0].toString());
                        }
                            kmLists.add(knowledgeEs);
                    }
                }
                return new AggregatedPageImpl<T>((List<T>) kmLists,pageable,hits.getTotalHits(),searchResponse.getAggregations());
            }
        });

        resultMap.put("rows",knowledgeEss.getContent());
        resultMap.put("total",knowledgeEss.getTotalElements());
        resultMap.put("pageNumer",pageNumber);
        return resultMap;
    }
}
