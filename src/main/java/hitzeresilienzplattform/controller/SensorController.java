package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Sensor;

import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
