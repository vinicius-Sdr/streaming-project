package fiap.pos.streaming.mapper;

import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VideoMapper {

    Video videoDTOtoEntity(VideoDTO videoDTO);
}
