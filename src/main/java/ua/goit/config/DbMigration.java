package ua.goit.config;

import org.flywaydb.core.Flyway;

public class DbMigration {

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(DataSourceHolder.getDataSource())
                .load();
        flyway.migrate();
    }
}
