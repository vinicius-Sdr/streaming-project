package fiap.pos.streaming.Model.DTO;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Model.dto.VideoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class videoDtoTest {
    @Test
    public void videoDtoModeltestCreate() {
        Category category = new Category("IdCategory", "Comedia");


        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setTitle("Call of duty 3");
        videoDTO.setDescription("Video mais legal do ano");
        videoDTO.setUrl("URL");
        videoDTO.setCategoria(category);
        videoDTO.setIsLiked(true);
        videoDTO.hashCode();
        videoDTO.toString();

        Assertions.assertNotNull(videoDTO);

    }
}
