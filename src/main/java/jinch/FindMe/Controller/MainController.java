package jinch.FindMe.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jinch.FindMe.DTO.MainDTO;
import jinch.FindMe.Service.MainService;

@Controller
public class MainController {

	//메인 서비스
	@Autowired
	MainService mainService;

	//인덱스
	@RequestMapping(value = "/")
	public String index() {
		System.out.println("index.jsp 이동...");
		return "index";
	}

	//인덱스에서 로그인시 가는 설명 페이지
	@RequestMapping(value = "/introduction")
	public String introduction() {
		return "introduction";
	}

	//설명페이지에서 확인버튼 클릭시 이동하는 페이지
	@RequestMapping(value="/snsFeedList")
	public String snsFeedList(MainDTO dto, Model model) {
		List<MainDTO> list = mainService.selectFeedList(null);
		model.addAttribute("list", list);
		return "snsFeedList";
	}

	//admin 계정으로 로그인 시 피드 업로드 페이지로 이동
	@RequestMapping(value="/snsFeedUpload")
	public String snsFeedUpload(MainDTO dto, Model model) {
		return "snsFeedUpload";
	}

	//피드 업로드하는 컨트롤
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
		mainService.uploadFeed(dto);//레코드 추가
		return "snsFeedUpload";
	}

	//정답입력시 정답을 확인하는 컨트롤
	@RequestMapping(value="/sendAnswer")
	public String sendAnswer(MainDTO dto, Model model) {
		//1번문제는 2, 2번 문제는 3 이런식
		int showLevel = 0;

		if("".equals(dto.getAnswer()) || dto.getAnswer() == null) {
			System.out.println("정답 입력 안함");
		}else if("진창휘".equals(dto.getAnswer())) {
			showLevel = 2;
		}else {
			System.out.println("정답이 아님");
		}

		//정답일경우 세팅된 showLevel의 값을 바꿔준다
		if(showLevel > 0) {
			dto.setShowLevel(showLevel);
			mainService.updateShowLevel(dto);
		}

		List<MainDTO> list = mainService.selectFeedList(null);
		model.addAttribute("list", list);
		return "snsFeedList";
	}

	@RequestMapping(value="/answerCheck")
	public @ResponseBody Map<String,Object> answerCheck(@RequestBody MainDTO dto, HttpServletRequest request) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();

		result.put("resultCode",dto.getAnswer());
		return result;
	}
}
