package fiap.pos.streaming.Service.Impl;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Repository.CategoryRepository;
import fiap.pos.streaming.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Mono<Category> createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }


}
