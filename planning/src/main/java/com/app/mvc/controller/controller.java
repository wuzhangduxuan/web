package com.app.mvc.controller;

import com.app.mvc.controller.common.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/15.
 */
@Controller
@RequestMapping("controller")

public class controller {

    @Resource
    private Person person;

    @RequestMapping("sd")
    public String disaptcher(){


        ModelAndView modelAndView = new ModelAndView("index");
        System.out.println("sdsdnjsa");
        return "index";
    }

    @RequestMapping("html")
    public String disaptcher1(){
        ModelAndView modelAndView = new ModelAndView("ind");
        System.out.println("sdsdnjsa");
        return "ind";
    }

    @RequestMapping("xml")
    public ModelAndView disaptcher2(){
        ModelAndView modelAndView = new ModelAndView("WelcomePage");
        System.out.println("sdsdnjsa");
        return modelAndView;
    }

    @RequestMapping("reso")
    public Person disaptcher3(Model model){
        person.setId(1);
        person.setName("reso");
        model.addAttribute("perosn",person);
        return person;
    }

}
