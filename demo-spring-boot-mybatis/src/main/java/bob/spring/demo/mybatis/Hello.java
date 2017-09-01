package bob.spring.demo.mybatis;

public class Hello {
	
	private static final long serialVersionUID = 1L;

    private Long id;
    private String sentence;
    private String language;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		return "Hello [id=" + id + ", sentence=" + sentence + ", language=" + language + "]";
	}

}
