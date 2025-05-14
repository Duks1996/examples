package com.example.controller;

import com.example.entity.Bus;
import com.example.entity.BusStop;
import com.example.entity.Stop;
import com.example.repository.BusRepository;
import com.example.repository.BusStopRepository;
import com.example.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bus-stop")
public class BusStopController {
    private BusRepository busRepository;
    private BusStopRepository busStopRepository;
    private StopRepository stopRepository;
    public BusStopController(BusRepository busRepository, BusStopRepository busStopRepository, StopRepository stopRepository) {
        this.busRepository = busRepository;
        this.busStopRepository = busStopRepository;
        this.stopRepository = stopRepository;
    }
    @PostMapping
    public ResponseEntity<BusStop> createBusStop(@RequestParam Long busId,@RequestParam Long stopId,@RequestBody BusStop busStop1) {
        Bus bus = busRepository.findById(busId).get();
        Stop stop = stopRepository.findById(stopId).get();

        busStop1.setBus(bus);
        busStop1.setStop(stop);
        BusStop saved = busStopRepository.save(busStop1);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
