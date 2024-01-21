package fiap.pos.streaming.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;

@Data
@Document("Videos")
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String url;

    @DocumentReference
    private Category categoria;
    private LocalDate publishDate;
    private Boolean isLiked;


}
