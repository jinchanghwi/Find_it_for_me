package jinch.FindMe.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jinch.FindMe.DTO.MainDTO;
import jinch.FindMe.Service.MainService;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class MainController {

	//메인 서비스
	@Autowired
	MainService mainService;

	/**
	 * <pre>
	 * @URL /
	 * @DESC 인덱스(로그인) 페이지로 이동한다
	 * 세션에 값이 있으면 로그인 된 것이니 피드리스트를 불러오고
	 * 세션에 값이 없으면 로그인하지 않았으니 로그인 페이지로 이동한다
	 * </pre>
	 */
	@RequestMapping(value = "/")
	public String index(MainDTO dto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String grade = (String) session.getAttribute("grade");
		if("".equals(grade) || null == grade) {
			System.out.println("index.jsp 이동...");
			return "index";
		}else {
			System.out.println("snsFeedList.jsp 이동...");
			return "redirect:/snsFeedList";
		}
	}

	/**
	 * <pre>
	 * @URL /login
	 * @DESC 로그인
	 * 화면에서 ID와 PW를 받아와 DB조회 후 값이 없으면 로그인 실패이며 로그인페이지로 이동한다
	 * 반대로 값이 있으면 세션에 해당유저의 등급을 등록하고 소개페이지로 이동한다
	 * </pre>
	 */
	@RequestMapping(value = "/login")
	public String login(MainDTO dto, Model model, HttpServletRequest request) {
		String grade = mainService.login(dto);
		if(null == grade || "".equals(grade)) {
			//로그인정보 없음
			return "index";
		}else{
			//로그인정보 있으면 패스
			HttpSession session = request.getSession();
			session.setAttribute("grade", grade); // 세션 설정
			return "introduction";
		}
	}

	//설명페이지에서 확인버튼 클릭시 이동하는 페이지
	/**
	 * <pre>
	 * @URL /snsFeedList
	 * @DESC 소개페이지에서 피드리스트로 이동
	 * 소개페이지에서 확인 버튼을 누르면 피드리스트로 이동한다
	 * </pre>
	 */
	@RequestMapping(value="/snsFeedList")
	public String snsFeedList(MainDTO dto, Model model) {
		List<MainDTO> list = mainService.selectFeedList(null);
		model.addAttribute("list", list);
		return "snsFeedList";
	}

	/**
	 * <pre>
	 * @URL /snsFeedUpload
	 * @DESC MASTER 피드 업로드
	 * MASTER 권한을 가진자가 피드리스트에서 업로드 버튼을 누르면 이동하는 페이지로
	 * 새롭게 피드를 업로드 할 수 있다
	 * </pre>
	 */
	@RequestMapping(value="/snsFeedUpload")
	public String snsFeedUpload(MainDTO dto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if("MASTER".equals((String)session.getAttribute("grade"))) {
			return "snsFeedUpload";
		}else {
			return "redirect:/snsFeedList";
		}
	}

	//피드 업로드하는 컨트롤
	/**
	 * <pre>
	 * @URL /uploadFeed
	 * @DESC 피드업로드(INSERT)
	 * MASTER 권한자가 피드 내용을 입력 후 업로드하면
	 * 파일업로드와 함께 각종 피드의 내용이 업로드 된다
	 * 이때 피드ID와 쇼레벨은 업로드과정에서 채번된다
	 * </pre>
	 */
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
		return "redirect:/snsFeedList";
	}

	//인덱스
	/**
	 * <pre>
	 * @URL /deleteFeed
	 * @DESC 피드를 삭제한다
	 * 화면에서 MASTER권한자가 삭제 버튼을 누르면 해당 피드ID를 가져와 DB에서 삭제한다
	 * </pre>
	 */
	@RequestMapping(value = "/deleteFeed")
	public String deleteFeed(MainDTO dto, Model model, HttpServletRequest request) {

		MainDTO asDto = mainService.selectFeed(dto);

		String path = request.getSession().getServletContext().getRealPath("/image/"); // 경로
		File delfile = new File(path + asDto.getImage()); // 삭제할 파일 지정
		if(delfile.exists()) {
			delfile.delete(); // 파일삭제
		}

		mainService.deleteFeed(dto);

		return "redirect:/snsFeedList";
	}

	/**
	 * <pre>
	 * @URL /answerCheck
	 * @DESC 정답체크
	 * 화면에서 최상단의 피드ID와 사용자가 입력한 정답을 받아
	 * 피드ID로 DB에서 세팅된 정답을 확인하고
	 * 사용자가 입력한 정답과 일치하면 0000코드를 던져준다
	 * 오답일 경우 9999코드를 던져준다
	 * </pre>
	 */
	@RequestMapping(value="/answerCheck")
	public @ResponseBody Map<String,Object> answerCheck(@RequestBody MainDTO dto, HttpServletRequest request) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		System.out.println(dto.toString());
		String reqAnswer = nvl(dto.getAnswer());
		MainDTO asDto = mainService.selectFeed(dto);
		String answer = nvl(asDto.getAnswer());
		if(reqAnswer.equals(answer)) {
			result.put("resultCode","0000"); //정답
			result.put("resultMsg","정답이라요"); //정답
			asDto.setShowLevel(asDto.getShowLevel()+1);
			mainService.updateShowLevel(asDto);
		}else {
			result.put("resultCode","9999"); //오답
		}
		return result;
	}

	public String nvl(String str) {
		if(str == null) {
			str = "";
		}
		return str;
	}
}
