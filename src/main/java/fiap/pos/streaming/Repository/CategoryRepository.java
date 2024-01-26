package fiap.pos.streaming.Repository;

import fiap.pos.streaming.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
