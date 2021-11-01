package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.DataSourceHolder;
import ua.goit.exceptions.SqlReturnedItemException;

import java.sql.*;
import java.util.Optional;

public class DbHelper {

    private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

    public static Long generateId(String sequenceName) {
        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
             Statement ps = connection.createStatement()) {
            ResultSet resultSet = ps.executeQuery("select nextval('" + sequenceName + "')");
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while trying do SQL request", e);
        }
        return 0L;
    }

    public static int executeWithPreparedStatement(String sql, ParameterSetter psCall) {

        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.set(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception while trying do SQL request", e);
            return 0;
        }
    }

    public static Optional<Long> executePreparedStatementAndGetId(String sql, ParameterSetter psCall) throws SqlReturnedItemException {
        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.set(ps);
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Optional.of(generatedKeys.getLong(1));
                } else {
                    throw new SqlReturnedItemException("Execution failed, no one entity was returned");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while trying do SQL request", e);
        }
        return Optional.empty();
    }

    public static ResultSet getWithPreparedStatement(String sql, ParameterSetter psCall) throws SQLException {
        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.set(ps);
            return ps.executeQuery();
        }
    }

    @FunctionalInterface
    public interface ParameterSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

}
