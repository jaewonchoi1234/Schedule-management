package com.sparta.schedulemanagement.service;


import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 생성
    public ScheduleResponseDto postSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);

        return responseDto;
    }

    //일정 목록 조회
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    //일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        return new ScheduleResponseDto(findScheduleById(id));
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {

        Schedule schedule = findScheduleById(id);

        if(requestDto.getPassword().equals(schedule.getPassword()))
        {
            schedule.update(requestDto);
            return new ScheduleResponseDto(schedule);
        }else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    //일정 삭제
    public Long deleteSchedule(Long id, String password) {

        Schedule schedule = findScheduleById(id);

        if(password.equals(schedule.getPassword())){
            scheduleRepository.delete(schedule);
            return id;
        }else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    //ID로 DB에서 일정 가져오는 함수
    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(()->
                new NullPointerException("선택한 일정은 삭제되어 있습니다."));
    }
}
