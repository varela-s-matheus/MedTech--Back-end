package com.br.medtech.controller;

import com.br.medtech.model.Schedule;
import com.br.medtech.service.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schedule")
@SecurityRequirement(name = "bearer-key")
public class ScheduleController {
    
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Schedule>> findScheduleById(@PathVariable Integer id) {
        return scheduleService.findScheduleById(id);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @PostMapping
    public ResponseEntity<Schedule> add(@RequestBody Schedule Schedule) {
        return scheduleService.add(Schedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> update(@PathVariable Integer id, @RequestBody Schedule Schedule) {
        return scheduleService.update(id, Schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Schedule> delete(@PathVariable Integer id) {
        return scheduleService.delete(id);
    }
}
