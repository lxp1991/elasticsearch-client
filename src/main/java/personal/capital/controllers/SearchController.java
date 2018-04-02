package personal.capital.controllers;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import personal.capital.services.ElasticsearchService;

@RestController
//@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;


    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity searchByField(String field, String value) {
        String results = elasticsearchService.searchByField(field.toLowerCase(), value.toLowerCase());
        return new ResponseEntity(results,HttpStatus.OK);
    }
}
