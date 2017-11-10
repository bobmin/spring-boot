package bob.spring.demo.heroku;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bob.spring.demo.heroku.UserService.User;

@RestController
@SpringBootApplication
public class Main {

	@Autowired
	private UserService userService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public UserService getUserService() {
		return new UserService();
	}

	@GetMapping("/user/{uid}/board")
	public String show(@PathVariable("uid") String uid, @RequestParam(value = "p", required = false) String pass) {
		User user = userService.find(uid, pass);
		return (null == user ? null : String.format("board from user %s: %s", user.getUid(), user.getIp()));
	}

	@PostMapping("/user/{uid}/board")
	public String createBoard(@PathVariable("uid") String uid, @RequestParam(value = "p", required = false) String pass,
			HttpServletRequest request) {
		User user = userService.find(uid, pass);
		if (null != user) {
			String ip = request.getRemoteAddr();
			user.setIp(ip);
		}
		return (null == user ? null : String.format("board for %s created: %s", user.getUid(), user.getIp()));
	}

	@PostMapping("/hash")
	public String createHash(@RequestParam("p") String pass) {
		final String hash = userService.makeHash(pass);
		return String.format("hash for %s computed: %s", pass, hash);
	}

}
