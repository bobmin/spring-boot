package bob.spring.demo.thymeleaf;

public class Hello {
	
	private String sentence;
    private String language;
    
    public Hello() {
    }
    
	public Hello(String sentence, String language) {
		this.sentence = sentence;
		this.language = language;
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
		return "Hello [sentence=" + sentence + ", language=" + language + "]";
	}

}
