package fiap.pos.streaming.Repository;

import fiap.pos.streaming.Model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
}
