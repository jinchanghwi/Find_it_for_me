package jinch.FindMe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jinch.FindMe.DTO.MainDTO;
import jinch.FindMe.Mapper.MainDAO;

@Service
public class MainServiceImpl implements MainService{

	//메인 DAO
	@Autowired
	MainDAO mainDao;

	/**
	 * <pre>
	 * @METHOD login
	 * @DESC 화면에서 ID와 PW를 받아와 DB에서 조회한다
	 * @PARAM 로그인정보
	 * @RETURN 등급(grade) or null
	 * </pre>
	 */
	@Override
	public String login(MainDTO dto) {
		return mainDao.login(dto);
	}

	/**
	 * <pre>
	 * @METHOD selectFeed
	 * @DESC 하나의 피드 정보를 가져온다
	 * @PARAM feedId
	 * @RETURN 피드정보
	 * </pre>
	 */
	@Override
	public MainDTO selectFeed(MainDTO dto) {
		return mainDao.selectFeed(dto);
	}

	/**
	 * <pre>
	 * @METHOD selectFeedList
	 * @DESC 피드 리스트를 불러온다
	 * @PARAM null
	 * @RETURN tbl_sns_feed 리스트(where showYN = 'Y')
	 * </pre>
	 */
	@Override
	public List<MainDTO> selectFeedList(MainDTO dto) {
		return mainDao.selectFeedList(dto);
	}

	/**
	 * <pre>
	 * @METHOD uploadFeed
	 * @DESC 피드 하나를 업로드한다
	 * 업로드 과정에서 피드ID와 쇼레벨을 자동으로 채번한다
	 * @PARAM 피드정보
	 * @RETURN void
	 * </pre>
	 */
	@Override
	public void uploadFeed(MainDTO dto) {
		String feedId = mainDao.createFeedId(dto);
		int showLevel = mainDao.createShowLevel(dto);
		dto.setFeedId(feedId);
		dto.setShowLevel(showLevel);
		mainDao.uploadFeed(dto);
		System.out.println("insert 완료!!");
	}

	/**
	 * <pre>
	 * @METHOD updateShowLevel
	 * @DESC 해당 피드의 showLevel+1 의 showYN을 Y로 업데이트 한다
	 * @PARAM showLevel
	 * @RETURN void
	 * </pre>
	 */
	@Override
	public void updateShowLevel(MainDTO dto) {
		mainDao.updateShowLevel(dto);
	}

	/**
	 * <pre>
	 * @METHOD deleteFeed
	 * @DESC 피드를 잘못 올렸을때 해당 피드를 삭제한다
	 * @PARAM feedId
	 * @RETURN void
	 * </pre>
	 */
	@Override
	public void deleteFeed(MainDTO dto) {
		mainDao.deleteFeed(dto);

	}
}
