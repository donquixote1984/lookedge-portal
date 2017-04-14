package lookedge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class HomeController{
    @RequestMapping(value="/")
    public String home(Map model){
        model.put("time",new Date());
        return "/home";
    }

    @RequestMapping(value="/test")
    public String test(Map model){
        return "test" ;
    }
}
