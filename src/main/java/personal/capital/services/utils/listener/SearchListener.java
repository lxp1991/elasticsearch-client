package personal.capital.services.utils.listener;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchResponse;


public class SearchListener implements ActionListener<SearchResponse> {


    @Override
    public void onResponse(SearchResponse searchResponse) {

    }

    @Override
    public void onFailure(Exception e) {

    }
}
