package ua.goit.dao;

import ua.goit.config.DataSourceHolder;

import java.sql.*;

public class DbHelper {

    public static int executeWithPreparedStatement(String sql, ParameterSetter psCall) {
        Connection connection;
        try {
            connection = DataSourceHolder.getDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                psCall.set(ps);
                return ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getWithPreparedStatement(String sql, ParameterSetter psCall) throws SQLException {
        Connection connection;
        connection = DataSourceHolder.getDataSource().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.set(ps);
            return ps.executeQuery();
        }
    }

    @FunctionalInterface
    public interface ParameterSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

}
