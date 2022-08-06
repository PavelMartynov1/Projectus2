package com.tradinghub.projectus2.controller.errorController;

import com.tradinghub.projectus2.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
@Controller
public class MyErrorController implements ErrorController {
    Logger logger = LoggerFactory.getLogger(MyErrorController.class);
    @GetMapping("/error")
    public ModelAndView resolveErrorPage(ModelAndView modelAndView, HttpServletRequest req){
        String errorCode=req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        String errorTemplateUrl="errorTemplates/"+errorCode+".html";
        modelAndView.setViewName(errorTemplateUrl);
        return modelAndView;
    }
}
