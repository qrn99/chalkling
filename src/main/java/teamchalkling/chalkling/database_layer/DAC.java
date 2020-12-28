package teamchalkling.chalkling.database_layer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public abstract class DAC {

    private final DataSource dataSource;

    public DAC(String dbURL) {
        if (dbURL == null || dbURL.isEmpty()) {
            dataSource = new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbURL);
            dataSource = new HikariDataSource(config);
        }
    }
}