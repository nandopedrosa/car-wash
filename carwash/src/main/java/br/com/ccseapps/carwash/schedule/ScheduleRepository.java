package br.com.ccseapps.carwash.schedule;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    
}
