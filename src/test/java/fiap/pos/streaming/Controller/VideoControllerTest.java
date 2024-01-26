package fiap.pos.streaming.Controller;

import fiap.pos.streaming.Model.Category;
import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import fiap.pos.streaming.Repository.VideoRepository;
import fiap.pos.streaming.Service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
public class VideoControllerTest {

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
    @InjectMocks
    private VideoController videoController;
    @Mock
    private VideoService videoService;
    @Mock
    private VideoRepository videoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        BDDMockito.when(videoService.findAll(1,
                10,
                "publishDate",
                "Desc",
                "zombies",
                LocalDate.now(),
                "Comedia")).thenReturn(Flux.just(page));

        BDDMockito.when(videoService.saveVideo(videoDTO)).thenReturn(Mono.just(video));

        BDDMockito.when(videoService.editVideo(video.getId(), videoDTO)).thenReturn(Mono.just(video));

        //validar teste com delete
//         BDDMockito.when(videoService.delete(video.getId())).thenReturn(Mono.just(null));

        BDDMockito.when(videoService.likeVideo(video.getId(), false)).thenReturn(Mono.just(video));
    }

    @Test
    @DisplayName("buscar todos os videos salvos")
    public void listAll_ReturnFluxOfVideo_WhenSuccessful() {
        StepVerifier.create(videoController.getAllVideos(1,
                        10,
                        "publishDate",
                        "Desc",
                        "zombies",
                        LocalDate.now(),
                        "Comedia").getBody())
                .expectNext(page);
    }

    @Test
    @DisplayName("create video")
    public void save_ReturnMonoOfVideo_WhenSuccessful() {
        StepVerifier.create(videoController.saveVideo(videoDTO).getBody())
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    @DisplayName("like video")
    public void likeVideo_ReturnMonoOfVideo_WhenSuccessful() {
        StepVerifier.create(videoController.likeVideo(video.getId(), true).getBody())
                .expectNext(video);

    }

    @Test
    @DisplayName("edit video")
    public void editVideo_ReturnMonoOfVideo_WhenSuccessful() {
        StepVerifier.create(videoController.editVideo(video.getId(), videoDTO).getBody())
                .expectNext(video)
                .verifyComplete();
    }


}
