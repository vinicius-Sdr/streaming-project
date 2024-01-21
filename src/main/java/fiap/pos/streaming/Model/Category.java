package fiap.pos.streaming.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document("Category")
public class Category {

    @Id
    private String id;

    private String name;

    @DocumentReference
    private List<Video> video;

}
