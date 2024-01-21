package fiap.pos.streaming.Service.Impl;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import fiap.pos.streaming.Repository.CategoryRepository;
import fiap.pos.streaming.Repository.VideoRepository;
import fiap.pos.streaming.Service.VideoService;
import fiap.pos.streaming.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private VideoMapper mapper;

    @Override
    public Mono<Video> saveVideo(VideoDTO videoDTO) {

        Category category = new Category();
        category.setName(videoDTO.getCategoria().getName());


        Video video = new Video();
        video.setTitle(videoDTO.getTitle());
        video.setDescription(videoDTO.getDescription());
        video.setUrl(videoDTO.getUrl());
        video.setCategoria(videoDTO.getCategoria());
        video.setIsLiked(videoDTO.getIsLiked());
        video.setPublishDate(LocalDate.now());

        return videoRepository.save(video);
    }

    @Override
    public Flux<Page<Video>> findAll(int page, int size, String order, String ordernation, String title, LocalDate publishDate, String category) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(order), ordernation);

        Query query = new Query().with(pageable);

        if (title != null) {
            query.addCriteria(Criteria.where("title").regex(title, "i"));
        }

        if (publishDate != null) {
            query.addCriteria(Criteria.where("publishDate").is(publishDate));
        }

        if (category != null) {
            query.addCriteria(Criteria.where("category").in(category));
        }

        return reactiveMongoTemplate.find(query, Video.class)
                .collectList()
                .map(videoList -> new PageImpl<>(videoList, pageable, videoList.size()))
                .flatMapMany(Flux::just);
    }


    //Falta ajustar a lógica de buscar quantos likes tem por categoria para então buscar todos os videos da categoria com mais liked

    @Override
    public Flux<Video> getRecomendation() {
//        Mono<List<String>> likedVideos = videoRepository.findVideoByIsLikedTrue()
//                .collectList()
//                .map(videoList -> videoList.stream().map(video -> Arrays.asList((video.getCategoria()))).flatMap(Collection::stream).collect(Collectors.toList()));

        Flux<Video> video = null;

        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return videoRepository.deleteById(id);
    }

    @Override
    public Mono<Video> editVideo(String id, VideoDTO videoDTO) {

        Mono<Video> video = videoRepository.findById(id);

        return video.flatMap((existingVideo) -> {
            existingVideo.setTitle(videoDTO.getTitle());
            existingVideo.setDescription(videoDTO.getDescription());
            existingVideo.setUrl(videoDTO.getUrl());
            existingVideo.setIsLiked(videoDTO.getIsLiked());
            existingVideo.setCategoria(videoDTO.getCategoria());
            return videoRepository.save(existingVideo);
        }).map((employee -> mapper.videoDTOtoEntity(videoDTO)));
    }

    @Override
    public Mono<Video> likeVideo(String id, boolean isLiked) {
        Mono<Video> video = videoRepository.findById(id);

        return video.flatMap((existingVideo) -> {
            existingVideo.setIsLiked(isLiked);
            return videoRepository.save(existingVideo);
        });

    }



}
