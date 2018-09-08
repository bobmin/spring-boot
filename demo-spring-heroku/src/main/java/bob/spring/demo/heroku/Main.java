package bob.spring.demo.heroku;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import bob.spring.demo.heroku.UserService.User;

@RestController
@SpringBootApplication
public class Main {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}

	@Bean
	public UserService getUserService() {
		return new UserService();
	}

	@Bean
	public BoardService getBoardService() {
		return new BoardService();
	}

	@GetMapping("/user/{uid}/board")
	public String show(@PathVariable("uid") String uid, @RequestParam(value = "p", required = false) String pass) {
		String x = null;
		User user = userService.find(uid, pass);
		if (null != user) {
			final List<String> ips = boardService.read(user.getUid());
			x = String.format("board from user %s: %s", user.getUid(), (null == ips ? null : ips.toArray()));
		}
		return x;
	}

	@PostMapping("/user/{uid}/board")
	public String createBoard(@PathVariable("uid") String uid, @RequestParam(value = "p", required = false) String pass,
			HttpServletRequest request) {
		String x = null;
		User user = userService.find(uid, pass);
		if (null != user) {
			String address = request.getRemoteAddr();
			boardService.save(user.getUid(), address);
			final List<String> ips = boardService.read(user.getUid());
			x = String.format("board for %s created: %s", user.getUid(), (null == ips ? null : ips.toArray()));

		}
		return x;
	}

	@PostMapping("/hash")
	public String createHash(@RequestParam("p") String pass) {
		final String hash = userService.makeHash(pass);
		return String.format("hash for %s computed: %s", pass, hash);
	}

}
