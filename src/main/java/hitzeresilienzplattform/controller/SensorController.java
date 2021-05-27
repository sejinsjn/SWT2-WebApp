package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Sensor;

import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorController {

    @Autowired
    private final SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Sensor addNewSensor(@RequestBody Sensor sensor) {
        return sensorRepository.save(sensor);
    }
}
