package com.br.medtech.service;

import com.br.medtech.model.Schedule;
import com.br.medtech.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ResponseEntity<Optional<Schedule>> findScheduleById(Integer id) {
        try {
            Optional<Schedule> Schedule = scheduleRepository.findById(id);

            if (Schedule.isPresent()) {
                return ResponseEntity.ok(Schedule);
            } throw new RuntimeException();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados. " + e);
        }
    }

    public ResponseEntity<List<Schedule>> findAllSchedules() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }

    public ResponseEntity<Schedule> add(Schedule schedule) {
        try {
            return ResponseEntity.ok(scheduleRepository.saveAndFlush(schedule));
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Schedule> update(Integer id, Schedule schedule) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados.");
        }
        schedule.setId(id);

        try {
            final Schedule updateSchedule = scheduleRepository.save(schedule);
            return ResponseEntity.ok(updateSchedule);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Schedule> delete(Integer id){
        if (!scheduleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado no banco de dados.");
        }

        try {
            scheduleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
