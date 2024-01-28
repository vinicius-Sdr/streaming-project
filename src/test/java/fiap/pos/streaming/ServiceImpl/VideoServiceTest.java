package fiap.pos.streaming.ServiceImpl;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import fiap.pos.streaming.Repository.CategoryRepository;
import fiap.pos.streaming.Repository.VideoRepository;
import fiap.pos.streaming.Service.Impl.VideoServiceImpl;
import fiap.pos.streaming.Service.VideoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VideoServiceTest {

    @InjectMocks
    private VideoServiceImpl videoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Mock
    private CategoryRepository categoryRepository;


    private final Category category = new Category("IdCategory", "Comedia");
    private final VideoDTO videoDTO = new VideoDTO("zombies",
            "video de zumbie",
            "URL",
            category,
            true);
    private final Video video = new Video("id",
            "zombies",
            "video de zumbie",
            "URL",
            category,
            LocalDate.now(),
            true);
    final Page<Video> page = new PageImpl<>(Arrays.asList(video));


    Pageable pageable = PageRequest.of(1, 10, Sort.Direction.valueOf("DESC"), "publishDate");
    Query query = new Query().with(pageable);
    PageImpl impl = new PageImpl<>(Arrays.asList(video));

    Optional<Category> optionalCategory = Optional.of(category);

    Mono<Void> voidReturn  = Mono.empty();


    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);

        BDDMockito.when(categoryRepository.findById(category.getId())).thenReturn(optionalCategory);

        BDDMockito.when(videoRepository.save(video)).thenReturn(Mono.just(video));

        BDDMockito.when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));

        BDDMockito.when(videoRepository.deleteById(video.getId())).thenReturn(voidReturn);

        BDDMockito.when(reactiveMongoTemplate.find(query, Video.class)).thenReturn(Flux.just());

//        BDDMockito.when(videoRepository.save(video)).thenReturn(Mono.just(video));

    }

    @Test
    void testCreateVideo_whenSuccessful(){

        StepVerifier.create(videoService.saveVideo(videoDTO)).expectNext(video);

    }

    @Test
    void testEditVideo_whenSuccessful(){

        StepVerifier.create(videoService.editVideo(video.getId(), videoDTO)).expectNext(video).verifyComplete();
    }

    @Test
    void testFindAllVideo_whenSuccessful(){

        StepVerifier.create(videoService.findAll(1,
                10,
                "DESC",
                "publishDate",
                "zombies",
                LocalDate.now(),
                "Comedia")).expectNext(page);
    }

    @Test
    void testLikeVideo_whenSuccessful(){
        StepVerifier.create(videoService.likeVideo(video.getId(), true)).expectNext(video).verifyComplete();
    }

    @Test
    void testDeleteVideo_whenSuccessful(){

        Assertions.assertNull(videoService.delete(Mockito.anyString()));
      //  StepVerifier.create(videoService.delete(video.getId())).expectNext().verifyComplete();
    }



}
