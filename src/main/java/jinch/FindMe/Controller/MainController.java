package jinch.FindMe.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value="/snsFeedList")
	public String snsFeedList(MainDTO dto, Model model) {
		List<MainDTO> list = mainService.selectFeedList(null);
		for(MainDTO data : list) {
			System.out.println(data.toString());
		}
		model.addAttribute("list", list);
		return "snsFeedList";
	}

	@RequestMapping(value="/snsFeedUpload")
	public String snsFeedUpload(MainDTO dto, Model model) {
		return "snsFeedUpload";
	}

	@RequestMapping(value="/uploadFeed")
	public String uploadFeed(MainDTO dto, Model model, HttpServletRequest request) throws Exception {
		MultipartFile uploadfile = dto.getUploadFile(); // 업로드 받기, vo 객체는 MultipartFile형으로
		String path = request.getSession().getServletContext().getRealPath("/image/"); //경로
		if(!uploadfile.isEmpty()) { //업로드가 존재하면
			String fileName = uploadfile.getOriginalFilename(); //실제 파일 이름
			File file = new File(path + fileName); //파일경로와 이름을 가지고
			if(file.exists()) { //파일이 이미 존재하면
				String onlyFileName = fileName.substring(0, fileName.indexOf(".")); //파일명
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //날짜포맷
				String time = sdf.format(new Date()); //중복방지용 날짜
				String extension = fileName.substring(fileName.indexOf(".")); //확장자
				fileName = onlyFileName+time+extension; //최종 파일이름
			}
			dto.setImage(fileName);
			uploadfile.transferTo(new File(path + fileName)); //저장하는 부분,java.io 임포트
		}
		else {
			dto.setImage("default.png"); //업로드가 없으면 기본 이미지
		}
		System.out.println(dto.toString());
		mainService.uploadFeed(dto);//레코드 추가
		return "snsFeedUpload";
	}


}
