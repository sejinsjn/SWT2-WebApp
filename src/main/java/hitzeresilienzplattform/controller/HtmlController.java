package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.service.ISensorService;
import hitzeresilienzplattform.entities.Baum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HtmlController {

    ISensorService sensorService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        List<Baum> baeume = sensorService.getAllSensor();
        if (baeume != null) model.addAttribute("baeume", baeume);
        return "index";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String getInterval(@RequestParam String interval, Model model){
        List<Baum> baeume = new LinkedList();
        if(interval.equals("All") || interval.equals(null)){
            baeume = sensorService.getAllSensor();
        }else{
            baeume = sensorService.findBySensorsTimestampGreaterThan(System.currentTimeMillis()-(86400000*Long.parseLong(interval)));
        }
        if (baeume != null) model.addAttribute("baeume", baeume);
        return "/fragments/charts :: charts";
    }


    @Autowired
    public void setSensorService(ISensorService sensorService){ this.sensorService = sensorService; }
}
