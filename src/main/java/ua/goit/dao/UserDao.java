package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.Group;
import ua.goit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    String getTableName() {
        return "users";
    }

    @Override
    public User mapToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setDescription(resultSet.getString("description"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return super.get(id)
                .map(group -> {
                    ResultSet usersByGroup = GroupDao.getInstance().getGroupsByUser(group.getId());
                    try {
                        List<Group> list = new ArrayList<>();
                        while (usersByGroup != null && usersByGroup.next()) {
                            list.add(GroupDao.getInstance().mapToEntity(usersByGroup));
                        }
                        group.setGroups(list);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return group;
                });
    }

    public User getByName(String userName) {
        String sql = "select * from users where name = ?";
        try (ResultSet rs = DbHelper.getWithPreparedStatement(sql, ps -> {
            ps.setString(1, userName);
        })) {
            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public ResultSet getUsersByGroup(Long groupId) {
        List<User> resultList = new ArrayList<>();
        String query = "select * from users where id in (select user_id from group_to_user where group_id = ?)";
        try {
            ResultSet resultSet = DbHelper.getWithPreparedStatement(
                    query, ps -> {
                        ps.setLong(1, groupId);
                    });
            return resultSet;
        } catch (SQLException e) {
            LOGGER.error("Get all method exception", e);
        }
        return null;
    }

}
