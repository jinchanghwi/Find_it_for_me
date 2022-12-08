package jinch.FindMe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jinch.FindMe.DTO.MainDTO;
import jinch.FindMe.Mapper.MainDAO;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	MainDAO mainDao;


	//로그인을 확인한다
	@Override
	public String login(MainDTO dto) {
		return mainDao.login(dto);
	}

	//피드ID를 채번한다
	@Override
	public String createFeedId(MainDTO dto) {
		return mainDao.createFeedId(dto);
	}
	//피드 리스트를 불러온다
	@Override
	public List<MainDTO> selectFeedList(MainDTO dto) {
		return mainDao.selectFeedList(dto);
	}
	//피드업로드
	@Override
	public void uploadFeed(MainDTO dto) {
		String feedId = createFeedId(dto);
		dto.setFeedId(feedId);
		mainDao.uploadFeed(dto);
		System.out.println(dto.toString());
		System.out.println("insert 완료!!");
	}
	//showLevel에 해당하는 레코드의 showYN을 Y로 업데이트
	@Override
	public void updateShowLevel(MainDTO dto) {
		mainDao.updateShowLevel(dto);
	}


}
