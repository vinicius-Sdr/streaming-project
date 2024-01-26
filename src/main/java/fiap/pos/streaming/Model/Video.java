package fiap.pos.streaming.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;

@Data
@Document("Videos")
@AllArgsConstructor
@NoArgsConstructor
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
