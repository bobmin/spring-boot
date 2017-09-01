package bob.spring.demo.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootApplication
public class DemoJdbcTemplateApplication {

	public static void main(String[] args) {
        SpringApplication.run(DemoJdbcTemplateApplication.class, args);
    }

	@Autowired
    private JdbcTemplate jdbcTemplate;

    public DemoJdbcTemplateApplication() {
    }

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
    	String sql = "select * from hello where language = 'DE'";
        return (args) -> System.out.println(jdbcTemplate.query(sql, new HelloRowMapper()));
    }
    
    private class HelloRowMapper implements RowMapper<Hello> {

		@Override
		public Hello mapRow(ResultSet rs, int rowNum) throws SQLException {
			Hello hello = new Hello();
			hello.setId(rs.getLong("id"));
			hello.setSentence(rs.getString("sentence"));
			hello.setLanguage(rs.getString("language"));
			return hello;
		}
    	
    }
    
}
