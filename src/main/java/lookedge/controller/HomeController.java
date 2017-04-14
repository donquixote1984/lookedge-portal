package lookedge.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class HomeController{
    @Value("${wp.server}")
    private String wpServer;

    @Value("${wp.port}")
    private int wpPort;

    @RequestMapping(value="/")
    public String home(Map model){
        model.put("time",new Date());
        return "/home";
    }

    @RequestMapping(value="/test")
    public String test(Map model){
        return "test" ;
    }

    @RequestMapping(value="/blog")
    public String blog(Map model){
        model.put("wpAddress", "//"+wpServer+":"+wpPort);
        return "/blog";
    }
    @RequestMapping(value="/about")
    public String about(){
        return "/about";
    }

    @RequestMapping(value="/doc")
    public String doc(){
        return "/doc";
    }
}
