package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry createEntry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO time_entries (project_id, user_id, "+
                    "date, hours) VALUES (?, ?, ?, ?)",RETURN_GENERATED_KEYS);
            statement.setLong(1, createEntry.getProjectId());
            statement.setLong(2, createEntry.getUserId());
            statement.setDate(3, Date.valueOf(createEntry.getDate()));
            statement.setInt(4, createEntry.getHours());
            return statement;
        }, keyHolder);
        return find(keyHolder.getKey().longValue());
    }

    private final RowMapper<TimeEntry> mapper = (rs, rowNum) -> new TimeEntry(
            rs.getLong("id"),
            rs.getLong("project_id"),
            rs.getLong("user_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("hours")
    );
    private final ResultSetExtractor<TimeEntry> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

    @Override
    public TimeEntry find(Long id) {
        return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
                new Object[]{id}, extractor);
    }

    @Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries",
                mapper);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry updateEntry) {
        jdbcTemplate.update("UPDATE time_entries " +
                        "SET project_id = ?, user_id = ?, date = ?,  hours = ? " +
                        "WHERE id = ?",
                updateEntry.getProjectId(),
                updateEntry.getUserId(),
                Date.valueOf(updateEntry.getDate()),
                updateEntry.getHours(),
                id);

        return find(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id);
    }
}
