package com.sparta.schedulemanagement.Repository;



import com.sparta.schedulemanagement.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
