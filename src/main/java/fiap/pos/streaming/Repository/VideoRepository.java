package fiap.pos.streaming.Repository;

import fiap.pos.streaming.Model.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface VideoRepository extends ReactiveMongoRepository<Video, String> {

    Flux<Video> findVideoByIsLikedTrue();



}
