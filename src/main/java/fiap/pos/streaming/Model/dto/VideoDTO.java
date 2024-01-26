package fiap.pos.streaming.Model.dto;

import fiap.pos.streaming.Model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    @NotBlank(message = "Titulo é um campo obrigatório")
    private String title;

    @NotBlank(message = "Descrição é um campo obrigatório")
    private String description;

    @NotBlank(message = "URL é um campo obrigatório")
    private String url;

    private Category categoria;

    private Boolean isLiked = false;

}
