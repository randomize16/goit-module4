package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    String getTableName() {
        return "users";
    }

    @Override
    User mapToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setDescription(resultSet.getString("description"));
        return user;
    }


    @Override
    public Optional<User> create(User user) {
        String sql = "insert into users(name, description) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, user.getName());
            ps.setString(2, user.getDescription());
        });
        LOGGER.info("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        String sql = "update users set name = ?, description = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, user.getName());
            ps.setString(2, user.getDescription());
            ps.setLong(3, user.getId());
        });
        LOGGER.info("Record was updated");
    }

}
