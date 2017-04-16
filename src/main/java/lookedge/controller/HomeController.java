package lookedge.controller;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController{
    @Value("${wp.server}")
    private String wpServer;

    @Value("${wp.port}")
    private int wpPort;

    @RequestMapping(value="/")
    public String home(Map model){
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

    @RequestMapping(value="/lang/{lang}")
    public String switchLang(@PathVariable String lang, HttpServletRequest request, HttpServletResponse response){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(lang.equals("zh")){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }
        else {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }

        return "/home";
    }
}
