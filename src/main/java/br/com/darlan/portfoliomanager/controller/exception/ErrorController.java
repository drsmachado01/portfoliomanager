package br.com.darlan.portfoliomanager.controller.exception;

import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.modelmbean.ModelMBean;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = NotFoundException.class)
    public String handleNotFoundException(ModelMap model, NotFoundException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
