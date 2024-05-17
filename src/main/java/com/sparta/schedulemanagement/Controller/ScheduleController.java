package com.sparta.schedulemanagement.Controller;


import com.sparta.schedulemanagement.Dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.Dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.Service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/schedule")
    public ScheduleResponseDto postSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.postSchedule(requestDto);
    }


    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }


    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }


    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }


    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(requestDto.getId(), requestDto.getPassword());
    }
}
