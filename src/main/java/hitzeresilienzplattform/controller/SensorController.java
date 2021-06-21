package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Baum;

import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {

    SensorRepository sensorRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Baum addNewSensor(@RequestBody Baum baum) { return sensorRepository.save(baum); }

    @RequestMapping(value = "/db/getAll", method = RequestMethod.GET)
    public List<Baum> getAllSensor(){  return sensorRepository.findAll(); }

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository){ this.sensorRepository = sensorRepository; }
}
