package personal.capital.services.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import personal.capital.services.ElasticsearchService;
import personal.capital.services.utils.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Async
    @Override
    public CompletableFuture<String> searchByFieldAsync(String field, String value) {
        final String[] result = {""};

        ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse response) {
                result[0] = Converter.convertSearchResponseToString(response);
            }

            @Override
            public void onFailure(Exception e) {

                result[0] = "";
            }
        };
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery(field, value));
        request.source(builder);
        client.searchAsync(request, listener);
        return CompletableFuture.completedFuture(result[0]);
    }

    @Override
    public List<String> getDistinctValues(String field) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        SearchResponse response = null;
        List<String> res = new ArrayList<>();
        try {
            response = client.search(request);
            Aggregations aggregation = response.getAggregations();
            Terms terms = aggregation.get(field);
            List<Terms.Bucket> buckets = (List<Terms.Bucket>) terms.getBuckets();
            for (Terms.Bucket bucket : buckets) {
                res.add(bucket.getKeyAsString());
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
