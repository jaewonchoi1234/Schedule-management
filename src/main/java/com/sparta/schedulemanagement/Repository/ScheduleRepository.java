package com.sparta.schedulemanagement.Repository;


import com.sparta.schedulemanagement.Dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.Dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.Entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule.schedule (title,contents,manager,password,date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getManager());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM schedule.schedule ORDER BY Date DESC";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 schedule데이터들을 scheduleResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String manager = rs.getString("manager");
                String password = rs.getString("password");
                String date = rs.getString("date");

                return new ScheduleResponseDto(id, title, contents, manager, password, date);
            }
        });
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule.schedule SET title = ?, contents = ?, manager = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule.schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule.schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setManager(resultSet.getString("manager"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setDate(resultSet.getString("date"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }


}
