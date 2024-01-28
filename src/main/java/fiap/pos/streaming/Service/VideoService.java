package fiap.pos.streaming.Service;

import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface VideoService {

    Mono<Video> saveVideo(VideoDTO videoDTO);

    Flux<Page<Video>> findAll(int page, int size, String order, String ordernation, String title, LocalDate publishDate, String category);

    Mono<Void> delete(String id);

    Mono<Video> editVideo(String id, VideoDTO videoDTO);

    Mono<Video> likeVideo(String id, boolean isLiked);

//    Flux<Video> getRecomendation();
}
