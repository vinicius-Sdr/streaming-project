package fiap.pos.streaming.Service.Impl;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Repository.CategoryRepository;
import fiap.pos.streaming.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


}
