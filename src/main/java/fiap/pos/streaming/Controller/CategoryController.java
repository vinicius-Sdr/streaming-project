package fiap.pos.streaming.Controller;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> saveVideo(@Valid @RequestParam String name) {
        return new ResponseEntity<>(categoryService.createCategory(name), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@Valid @PathVariable String id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.CREATED);
    }

}
