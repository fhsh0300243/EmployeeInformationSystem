package tw.eis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {

	@RequestMapping(path = "/FeeAllPage.action",method =RequestMethod.GET)
	public String MinPage() {
		return "FeeAllPage";
	}
	@RequestMapping(path = "/AddFeeApp.action",method =RequestMethod.GET)
	public String feeAppPage() {
		return "feeApplicationForm";
	}
	

}
