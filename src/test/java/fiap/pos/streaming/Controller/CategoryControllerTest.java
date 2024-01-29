package fiap.pos.streaming.Controller;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    private final Category category = new Category("123123id", "Comedia");
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;




    @BeforeEach
    public void setUp() {
        BDDMockito.when(categoryService.createCategory(category.getName()))
                .thenReturn(category);

        BDDMockito.when(categoryService.findById(Mockito.anyString())).thenReturn(category);

        BDDMockito.when((categoryService.findAll())).thenReturn(Arrays.asList(category));


    }

    @Test
    @DisplayName("Create category")
    public void save_createCategory_WhenSuccessful() {
        Assertions.assertNotNull(categoryController.saveVideo(Mockito.anyString()));
    }

    @Test
    @DisplayName("Find category By Id")
    public void FindById_Category_WhenSuccessful() {
        Assertions.assertNotNull(categoryController.findById(Mockito.anyString()));
    }

    @Test
    @DisplayName("Find all category")
    public void findAll_Category_WhenSuccessful() {
        Assertions.assertNotNull(categoryController.findAllCategory());
    }


}
