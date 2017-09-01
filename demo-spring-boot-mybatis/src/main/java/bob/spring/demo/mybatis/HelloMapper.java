package bob.spring.demo.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface HelloMapper {
	
    @Select("SELECT id, sentence, language FROM hello")
    List<Hello> findAllHellos();

    @Insert("INSERT INTO hello(sentence,language) VALUES(#{sentence},#{language})")
    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=Long.class)
    void insertHello(Hello hello);
	
    @Select("SELECT id, sentence, language FROM hello WHERE id=#{id}")
    Hello findById(Long id);
    
	@Select("SELECT id, sentence, language FROM hello WHERE language = #{language}")
    Hello findByLanguage(String language);
	
}
