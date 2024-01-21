package fiap.pos.streaming.Service;

import fiap.pos.streaming.Model.Category;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Mono<Category> createCategory(String name);

    Mono<Category> findById(String id);

}
