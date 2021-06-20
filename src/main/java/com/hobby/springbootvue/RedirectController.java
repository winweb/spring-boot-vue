package com.hobby.springbootvue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//Method 2 for fix HTML5 history mode in Vue
@Slf4j
@Controller
public class RedirectController {

    @GetMapping({"/home", "/about"})
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {

        log.debug("forward to index.html");

        return new ModelAndView("forward:/index.html", model);
    }
}
