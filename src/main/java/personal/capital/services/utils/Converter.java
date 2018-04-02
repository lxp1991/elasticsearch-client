package personal.capital.services.utils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class Converter {


    public static String convertSearchResponseToString(SearchResponse response) {

        SearchHits hits = response.getHits();
        StringBuilder sb = new StringBuilder();
        for (SearchHit hit : hits) {
            sb.append(hit.getSourceAsString());
        }
        return sb.toString();

    }
}
