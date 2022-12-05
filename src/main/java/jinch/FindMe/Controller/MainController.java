package jinch.FindMe.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jinch.FindMe.DTO.MainDTO;
import jinch.FindMe.Service.MainService;

@Controller
public class MainController {

	@Autowired
	MainService mainService;

	@RequestMapping(value = "/")
	public String index() {
		System.out.println("index.jsp 이동...");
		return "index";
	}

	@RequestMapping(value="/snsList")
	public String sns(MainDTO dto, Model model) {
		List<MainDTO> list = mainService.selectFeedList(null);
		for(MainDTO data : list) {
			System.out.println(data.toString());
		}
		model.addAttribute("list", list);
		return "snsList";
	}

}
