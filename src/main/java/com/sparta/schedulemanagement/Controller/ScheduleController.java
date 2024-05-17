package com.sparta.schedulemanagement.Controller;


import com.sparta.schedulemanagement.Dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.Dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.Service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api
@RestController
@RequestMapping("/api")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @ApiOperation(value = "일정 등록")
    @PostMapping("/schedule")
    public ScheduleResponseDto postSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.postSchedule(requestDto);
    }


    @ApiOperation(value = "일정 목록 조회")
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }


    @ApiOperation(value = "일정 조회")
    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }


    @ApiOperation(value = "일정 수정")
    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }


    @ApiOperation(value = "일정 삭제")
    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto.getPassword());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleCustomException(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
