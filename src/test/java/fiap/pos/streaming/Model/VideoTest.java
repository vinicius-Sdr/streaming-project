package fiap.pos.streaming.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VideoTest {

    @Test
    public void videoModaltestCreate() {
        Category category = new Category("IdCategory", "Comedia");


        Video video = new Video();
        video.setTitle("Call of duty 3");
        video.setDescription("Video mais legal do ano");
        video.setUrl("URL");
        video.setCategoria(category);
        video.setIsLiked(true);
        video.setPublishDate(LocalDate.now());
        video.hashCode();
        video.toString();

        Assertions.assertNotNull(video);

    }

}
