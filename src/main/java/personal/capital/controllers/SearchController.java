package personal.capital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import personal.capital.services.ElasticsearchService;

import java.util.List;
import java.util.concurrent.Future;

@RestController
//@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;


    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public String searchByField(String field, String value) {
        String result = elasticsearchService.searchByField(field.toLowerCase(), value.toLowerCase());
        return result;
    }

    @RequestMapping(value = "/searchAsync", method = RequestMethod.GET, produces = "application/json")
    public Future<String> searchByFieldAsync(String field, String value) {
        Future<String> result = elasticsearchService.searchByFieldAsync(field.toLowerCase(), value.toLowerCase());
        return result;
    }

    @RequestMapping(value = "/distinctValues", method = RequestMethod.GET, produces = "application/json")
    public List<String> distinctValues(String field) {
        List<String> result = elasticsearchService.getDistinctValues(field.toLowerCase());
        return result;
    }
}
