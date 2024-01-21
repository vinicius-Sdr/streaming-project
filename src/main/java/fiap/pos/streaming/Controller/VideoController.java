package fiap.pos.streaming.Controller;

import fiap.pos.streaming.Model.Video;
import fiap.pos.streaming.Model.dto.VideoDTO;
import fiap.pos.streaming.Service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/streaming")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<Mono<Video>> saveVideo(@Valid @RequestBody VideoDTO videoDto){
        return new ResponseEntity<>(videoService.saveVideo(videoDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Mono<Video>> editVideo(@Valid @PathVariable String id, @Valid @RequestBody VideoDTO videoDTO){
        return new ResponseEntity<>(videoService.editVideo(id, videoDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/favoritos/{id}")
    public ResponseEntity<Mono<Video>> likeVideo(@Valid @PathVariable String id, @Valid @RequestParam boolean isLiked){
        return new ResponseEntity<>(videoService.likeVideo(id, isLiked), HttpStatus.OK);
    }


    @DeleteMapping( value = "/{id}")
    public void deleteVideo(@Valid @PathVariable String id){
         videoService.delete(id).subscribe();
    }

    @GetMapping("/paginado")
    public ResponseEntity<Flux<Page<Video>>> getAllVideos(
            @Valid @RequestParam(defaultValue = "0") int page,
            @Valid @RequestParam(defaultValue = "10") int size,
            @Valid @RequestParam(defaultValue = "DESC") String order,
            @Valid @RequestParam(defaultValue = "publishDate") String ordernation,
            @Valid @RequestParam(required = false) String title,
            @Valid @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate,
            @Valid @RequestParam(required = false) String category) {

        Flux<Page<Video>> response = videoService.findAll(page, size, order.toUpperCase(), ordernation, title, publishDate, category);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/recomendados")
    public ResponseEntity<Flux<Video>> getRecomendation(){

        Flux<Video> response = videoService.getRecomendation();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
