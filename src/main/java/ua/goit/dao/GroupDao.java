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

public class GroupDao extends AbstractDao<Group> {

    private static GroupDao instance;

    private static final Logger LOGGER = LogManager.getLogger(GroupDao.class);

    private GroupDao() {
    }

    public static GroupDao getInstance() {
        if (instance == null) {
            instance  = new GroupDao();
        }
        return instance;
    }

    @Override
    String getTableName() {
        return "groups";
    }

    @Override
    public Optional<Group> get(Long id) {
        return super.get(id)
                .map(group -> {
                    ResultSet usersByGroup = UserDao.getInstance().getUsersByGroup(group.getId());
                        try {
                            List<User> list = new ArrayList<>();
                            while(usersByGroup != null && usersByGroup.next()) {
                                list.add(UserDao.getInstance().mapToEntity(usersByGroup));
                            }
                            group.setUsers(list);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return group;
                });
    }

    @Override
    public Group mapToEntity(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong("id"));
        group.setName(rs.getString("name"));
        return group;
    }

    @Override
    public Optional<Group> create(Group entity) {
        return Optional.empty();
    }

    @Override
    public void update(Group entity) {

    }

    public ResultSet getGroupsByUser(Long userId) {
        String query = "select * from groups where id in (select group_id from group_to_user where user_id = ?)";
        try {
            ResultSet resultSet = DbHelper.getWithPreparedStatement(
                    query, ps -> {
                        ps.setLong(1, userId);
                    });
            return resultSet;
        } catch (SQLException e) {
            LOGGER.error("Get all method exception", e);
        }
        return null;
    }
}
