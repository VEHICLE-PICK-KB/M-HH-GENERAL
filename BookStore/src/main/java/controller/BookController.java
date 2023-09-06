package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class BookController {


	@GetMapping(value= {"/index"})
    public String Bookfunction(Model model) {	
    	//model.addAttribute(" ",TODO );
    		
        return "//TODO";
    }
}
