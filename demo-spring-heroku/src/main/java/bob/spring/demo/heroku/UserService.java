package bob.spring.demo.heroku;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Value;

public class UserService {

	private static final List<User> DATA = new LinkedList<>();

	static {
		DATA.add(new User("klon", "SL7/L1pXQtZkphHZLYnw5wh1BDi58AjCb1VMA+IFDO0="));
	}

	private static final Logger LOG = Logger.getLogger(UserService.class.getName());

	@Value("${bob-demo-spring-heroku.algorithm}")
	private String algorithm;

	public UserService() {
		LOG.info("algorithm assigned: " + algorithm);
	}

	public User find(String uid, String pass) {
		User user = null;
		lookFor: for (User x : DATA) {
			if (x.uid.equals(uid)) {
				String hash = makeHash(pass);
				if (x.hash.equals(hash)) {
					user = x;
				}
				break lookFor;
			}
		}
		return user;
	}

	/**
	 * Berechnet den Hash und liefert seine Base64-Entsprechung.
	 * 
	 * @param pass
	 *            der Ausgangswert
	 * @return eine Zeichenkette, niemals <code>null</code>
	 */
	public String makeHash(final String pass) {
		Objects.requireNonNull(pass);
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(pass.getBytes());
			return Base64.getEncoder().encodeToString((messageDigest.digest()));
		} catch (NoSuchAlgorithmException ex) {
			throw new WebServiceException(ex);
		}
	}

	public static class User {

		private final String uid;

		private final String hash;

		private String ip = null;

		public User(String uid, String hash) {
			Objects.requireNonNull(uid);
			Objects.requireNonNull(hash);
			this.uid = uid;
			this.hash = hash;
		}

		public String getUid() {
			return uid;
		}

		public void setIp(String value) {
			this.ip = value;
		}

		public String getIp() {
			return ip;
		}

	}

}
