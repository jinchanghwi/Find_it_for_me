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

	@Override
	public List<MainDTO> selectFeedList(MainDTO dto) {
		return mainDao.selectFeedList(dto);
	}


}
