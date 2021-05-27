package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.Sensor;
import hitzeresilienzplattform.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository){ this.sensorRepository = sensorRepository; }
}
