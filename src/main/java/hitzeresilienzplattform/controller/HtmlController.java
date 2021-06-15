package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HtmlController {

    SensorRepository sensorRepository;

    @GetMapping("/")
    public String displayIndex(Model model) {
        List<Sensor> sensors = sensorRepository.findAll();
        if (sensors != null) model.addAttribute("sensors", sensors);
        return "index";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String getInterval(@RequestParam String interval, Model model){
        List<Sensor> sensors = new LinkedList();
        if(interval.equals("All") || interval.equals(null)){
            sensors = sensorRepository.findAll();
        }else{
            sensors = sensorRepository.findBySensorsTimestampGreaterThan(System.currentTimeMillis()-(86400000*Long.parseLong(interval)));
        }
        if (sensors != null) model.addAttribute("sensors", sensors);
        return "/fragments/charts :: charts";
    }

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository){ this.sensorRepository = sensorRepository; }
}
