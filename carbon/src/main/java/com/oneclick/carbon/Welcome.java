package com.oneclick.carbon;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class Welcome {
    @RequestMapping("/index")
    public String loginMessage(){
        return "welcome";
    }

}



