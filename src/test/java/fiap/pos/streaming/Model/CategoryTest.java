package fiap.pos.streaming.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void categotyModaltestCreate() {
        Category category = new Category();
        category.setName("Comedia");
        category.setId("Id_category");

        category.hashCode();
        category.toString();

        Assertions.assertNotNull(category);

    }

}
