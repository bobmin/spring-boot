package bob.spring.demo.heroku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardService {

	// @formatter:off
	private static final  String CREATE = "CREATE TABLE IF NOT EXISTS board ("
			+ "user varchar(max), "
			+ "address varchar(max), "
			+ "tick timestamp)";  
	// @formatter:on

	@Autowired
	private DataSource dataSource;

	public BoardService() {
	}

	public void save(final String user, final String address) {
		try (Connection connection = dataSource.getConnection()) {
			connection.createStatement().executeUpdate(CREATE);
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO board VALUES (?, ?, now())");
			stmt.setString(1, user);
			stmt.setString(1, address);
			stmt.executeUpdate();
		} catch (Exception e) {
			// model.put("message", e.getMessage());
		}
	}

	public List<String> read(String user) {
		try (Connection connection = dataSource.getConnection()) {
			connection.createStatement().executeUpdate(CREATE);
			PreparedStatement stmt = connection
					.prepareStatement("SELECT TOP 10 tick FROM user WHERE user = ? ORDER BY tick DESC");
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();

			List<String> output = new ArrayList<String>();
			while (rs.next()) {
				if (0 < output.size()) {
					output.add(", ");
				}
				output.add(rs.getString("address"));
			}
			return output;
		} catch (Exception e) {
			// model.put("message", e.getMessage());
			return null;
		}
	}

}
