package fiap.pos.streaming.Service;

import fiap.pos.streaming.Model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(String name);

    Category findById(String id);

    List<Category> findAll();
}
