package bob.spring.demo.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	@Autowired
	private HelloMapper helloMapper;

	public Hello getHello(long id) {
		return helloMapper.findById(id);
	}

	public List<Hello> getAllHellos() {
		return helloMapper.findAllHellos();
	}

	public void addHello(Hello hello) {
		helloMapper.insertHello(hello);
	}

	public void updateHello(long id, Hello hello) {
		// TODO Auto-generated method stub
		
	}

	public void deleteHello(long id) {
		// TODO Auto-generated method stub
		
	}

}
