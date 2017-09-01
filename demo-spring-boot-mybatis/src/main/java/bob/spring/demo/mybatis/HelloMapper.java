package bob.spring.demo.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HelloMapper {

	@Select("SELECT id, sentence, language FROM hello WHERE language = #{language}")
    Hello findByLanguage(String language);
	
}
