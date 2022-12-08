package jinch.FindMe.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MainDTO {

	private String userId;				//유저ID
	private String userPw;				//유저PW
	private String grade;				//등급
	private String feedId;				//피드ID
	private String writerId;			//작성자ID
	private String text;				//텍스트
	private String image;				//이미지
	private MultipartFile uploadFile;	//파일 업로드
	private String showYN;				//화면표시여부
	private int showLevel;				//화면표시순서
	private String answer;				//정답



}