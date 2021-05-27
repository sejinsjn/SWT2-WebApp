package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Sensor;

import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {

    SensorRepository sensorRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Sensor addNewSensor(@RequestBody Sensor sensor) { return sensorRepository.save(sensor); }

    @RequestMapping(value = "/db/getAll", method = RequestMethod.GET)
    public List<Sensor> getAllSensor(){
        return sensorRepository.findAll();
    }

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository){ this.sensorRepository = sensorRepository; }
}
