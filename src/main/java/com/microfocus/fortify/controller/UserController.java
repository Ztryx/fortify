package com.microfocus.fortify.controller;

/**
 * Created by andyx on 12/5/18.
 */

import com.microfocus.fortify.model.User;
import com.microfocus.fortify.repository.UserJdbcRepository;
import com.microfocus.fortify.utils.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    UserJdbcRepository repository;

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("home", new User());
        model.addAttribute("users", repository.findAll());
        return "home";
    }

    @PostMapping(value = "/newUser")
    @ResponseBody
    public ResponseEntity<?> saveEmployee(@ModelAttribute @Valid User user,
                                          BindingResult result) {
        ResponseUser resp = new ResponseUser();

        if(result.hasErrors()){
            //Get error message
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            return ResponseEntity.badRequest().body(result);
        }else{
            if(repository.findByEmail(user.getName()) == null) {
                repository.insert(new User(user.getId(), user.getName(), user.getEmail()));
                resp.setCodeStatus("1");
                resp.setMessage("The user was succesfully created!");
            }
            else {
                resp.setCodeStatus("2");
                resp.setMessage("The email already exists, please try other one!");
            }

            resp.setUsers(repository.findAll());

        }
        return ResponseEntity.ok(resp);
    }
}
