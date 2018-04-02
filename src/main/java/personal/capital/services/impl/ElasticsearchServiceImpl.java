package personal.capital.services.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import personal.capital.services.ElasticsearchService;
import personal.capital.services.utils.Converter;

import java.io.IOException;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private static String ES_HOST = "search-personal-capital-ar4adi5d2ldej7qpyxojgtvoay.us-east-1.es.amazonaws.com";
    private static int ES_PORT = 443;
    private static String ES_SCHEME = "https";
    private RestHighLevelClient client;

    public ElasticsearchServiceImpl() {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));
    }

    @Override
    public String searchByField(String field, String value) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery(field, value));
        request.source(builder);
        try {
            SearchResponse response = client.search(request);
            return Converter.convertSearchResponseToString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
