package org.example.dao.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceLockRepository {
    private final JdbcTemplate jdbcTemplate;

    public DeviceLockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void lockDeviceByIdAndAvailable(Integer deviceId, boolean isAvailable) {
        String sql = "SELECT * FROM Device WHERE id = ? AND available = ? FOR UPDATE";
        jdbcTemplate.query(sql, new Object[]{deviceId, isAvailable}, rs -> {
            // This block is executed only when the row is locked
            // Perform any additional actions or checks here
        });
    }
}