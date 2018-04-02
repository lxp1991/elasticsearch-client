package personal.capital.services;

public interface ElasticsearchService {
    String searchByField(String field, String value);
}
