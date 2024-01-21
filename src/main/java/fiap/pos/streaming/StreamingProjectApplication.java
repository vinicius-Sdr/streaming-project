package fiap.pos.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"fiap.pos", "fiap.pos.streaming", "fiap.pos.streaming.mapper"})
@SpringBootApplication
public class StreamingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamingProjectApplication.class, args);
    }

}
