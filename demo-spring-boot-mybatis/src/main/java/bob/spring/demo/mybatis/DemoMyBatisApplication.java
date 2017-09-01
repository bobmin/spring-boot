package bob.spring.demo.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMyBatisApplication {

	public static void main(String[] args) {
        SpringApplication.run(DemoMyBatisApplication.class, args);
    }

    private final HelloMapper helloMapper;

    public DemoMyBatisApplication(HelloMapper helloMapper) {
        this.helloMapper = helloMapper;
    }

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        return (args) -> System.out.println(this.helloMapper.findByLanguage("DE"));
    }
    
}
