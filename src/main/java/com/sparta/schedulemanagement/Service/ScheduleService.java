package com.sparta.schedulemanagement.Service;


import com.sparta.schedulemanagement.Dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.Dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.Entity.Schedule;
import com.sparta.schedulemanagement.Repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto postSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);

        return responseDto;
    }

    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAll();
    }

    public ScheduleResponseDto getSchedule(Long id) {
        return new ScheduleResponseDto(scheduleRepository.findById(id));
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        if(requestDto.getPassword().equals(scheduleRepository.findById(id).getPassword())){
            scheduleRepository.update(id, requestDto);
            return new ScheduleResponseDto(scheduleRepository.findById(id));
        }else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }


    public Long deleteSchedule(Long id, String password) {
        if(password.equals(scheduleRepository.findById(id).getPassword())){
            scheduleRepository.delete(id);
            return id;
        }else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }


}
