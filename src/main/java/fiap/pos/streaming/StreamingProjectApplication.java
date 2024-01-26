package fiap.pos.streaming;

import fiap.pos.streaming.Repository.CategoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({"fiap.pos", "fiap.pos.streaming", "fiap.pos.streaming.mapper"})
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {CategoryRepository.class, MongoTemplate.class})
public class StreamingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamingProjectApplication.class, args);
    }

}
