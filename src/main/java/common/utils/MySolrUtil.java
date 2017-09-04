package common.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import java.io.IOException;
import java.util.*;

/**
 * @author zouyang
 * @date 2017/8/31 17:04
 * @description solr操作工具类
 */
public class MySolrUtil {

    private static ConcurrentUpdateSolrClient updateSolrClient;//适合新增更新删除
    private static String solrUrl;

    static {
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("solrProperties"));
//            solrUrl = properties.getProperty("solr.dependency");
            solrUrl = "http://localhost:8983/solr";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author zouyang
     * @date 2017/8/31 20:56
     * @description Solr查询
     * @Params coreName solr实体名，paramsMap 查询条件,fuzzyQueryParamsMap 模糊查询条件，sortMap 排序条件，page 分页信息
     */
    public static Map<String, Object> solrQuery(String coreName, Map<String, String> paramsMap, Map<String,String> fuzzyQueryParamsMap,Map<String, String> sortMap, Page page) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        String url = solrUrl + "/" + coreName;
        //HttpSolrClient 适合查询,ConcurrentUpdateSolrClient适合新增修改
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(url).build();
        SolrQuery solrQuery = new SolrQuery();
        int beginNum = (page.getPageNo() - 1) * page.getRowsPerPage();
        solrQuery.setStart(beginNum);
        solrQuery.setRows(page.getRowsPerPage());
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("*:*");
        if (paramsMap != null && !paramsMap.isEmpty()) {
            //精准查询条件,solr查询，条件加双引号就是精准查询如，name:"张三",不加双引号就是模糊查询如，name:张
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                queryStr.append(" AND ");
                queryStr.append(entry.getKey() + ":\"" + entry.getValue()+"\"");
            }
        }else if(fuzzyQueryParamsMap !=null && !fuzzyQueryParamsMap.isEmpty()){
            queryStr.append(" AND (");
            //模糊查询的条件
            for (Map.Entry<String, String> entry : fuzzyQueryParamsMap.entrySet()) {
                queryStr.append(entry.getKey() + ":" + entry.getValue());
                queryStr.append(" AND ");
            }
            queryStr.delete(queryStr.length()-5,queryStr.length());
            queryStr.append(")");
        }
        solrQuery.setQuery(queryStr.toString());
        if (sortMap != null) {
            for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                solrQuery.addSort(entry.getKey(), "desc".equals(entry.getValue().toLowerCase()) ? SolrQuery.ORDER.desc : SolrQuery.ORDER.asc);
            }
        }
        try {
            QueryResponse response = httpSolrClient.query(solrQuery);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                SolrDocument sd = list.get(i);
                Map<String,Object> m = new HashMap<String, Object>();
                for(Map.Entry<String,Object> entry : sd.entrySet()){
                    m.put(entry.getKey(),entry.getValue());
                }
                resultList.add(m);
            }
            resultMap.put("data", resultList);
            resultMap.put("count", list.getNumFound());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * @author zouyang
     * @date 2017/8/31 21:00
     * @description 根据Id删除
     */
    public static void solrDelete(String coreName, int id) {
        String url = solrUrl + "/" + coreName;
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(url).build();
        try {
            httpSolrClient.deleteById(String.valueOf(id));
            httpSolrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solrUpdate(String coreName, Map<String, String> paramsMap) {
        String url = solrUrl + "/" + coreName;
        SolrClient updateSolrClient = new ConcurrentUpdateSolrClient.Builder(url).withQueueSize(5).withThreadCount(5).build();
        try {
            SolrInputDocument inputDocument = new SolrInputDocument();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                inputDocument.addField(entry.getKey(), entry.getValue());
            }
            updateSolrClient.add(inputDocument);
            updateSolrClient.commit();
            inputDocument.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
