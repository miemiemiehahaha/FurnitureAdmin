package FurnitureAdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//��̬ҳ����ת������

@Controller
public class JMPController {
	

		@RequestMapping(value="/{page}")
	    public String showPage(@PathVariable String page){
	        return page;
	    }

}
