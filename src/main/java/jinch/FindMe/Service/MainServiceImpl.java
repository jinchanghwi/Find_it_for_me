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


}
