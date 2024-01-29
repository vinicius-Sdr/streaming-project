package fiap.pos.streaming.ServiceImpl;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Repository.CategoryRepository;
import fiap.pos.streaming.Service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Optional;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private CategoryRepository categoryRepository;

    private final Category category = new Category("IdCategory", "Comedia");


    Optional<Category> optionalCategory = Optional.of(category);

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);

        BDDMockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(optionalCategory);
        BDDMockito.when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));
        BDDMockito.when(categoryRepository.save(category)).thenReturn(category);

    }

    @Test
    public void saveCategory_whenSuccessful(){
        Category cat = categoryService.createCategory("Comedia");
        Assertions.assertNotNull(category);
    }

    @Test
    public void findCategoryById_whenSuccessful(){
        Assertions.assertNotNull(categoryService.findById(category.getId()));

    }

    @Test
    public void findAllCategory_whenSuccessful(){
        Assertions.assertNotNull(categoryService.findAll());

    }



}
