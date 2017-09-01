package bob.spring.demo.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;

	@RequestMapping("/hellos")
	public List<Hello> getAllJobs() {
		return helloService.getAllHellos();
	}
	
	@RequestMapping("/hellos/{id}")
	public Hello getHello(@PathVariable("id") long id) {
		return helloService.getHello(id);
	}	
	
	@RequestMapping(method=RequestMethod.POST, value="/hellos")
	public void addJob(@RequestBody Hello hello) {
		helloService.addHello(hello);
	}	
	
	@RequestMapping(method=RequestMethod.PUT, value="/hellos/{id}")
	public void updateJob(@PathVariable("id") long id, @RequestBody Hello hello) {
		helloService.updateHello(id, hello);
	}	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/hellos/{id}")
	public void updateJob(@PathVariable("id") long id) {
		helloService.deleteHello(id);
	}	
	
}
