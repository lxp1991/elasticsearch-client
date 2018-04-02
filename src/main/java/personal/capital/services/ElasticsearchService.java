package personal.capital.services;

import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ElasticsearchService {
    String searchByField(String field, String value);

    @Async
    CompletableFuture<String> searchByFieldAsync(String field, String value);

    List<String> getDistinctValues(String field);
}
