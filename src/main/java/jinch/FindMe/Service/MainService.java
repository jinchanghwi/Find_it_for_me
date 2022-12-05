package jinch.FindMe.Service;

import java.util.List;

import jinch.FindMe.DTO.MainDTO;

public interface MainService {

	//피드ID채번
	public String createFeedId(MainDTO dto);

	//피드리스트를 가져온다
	public List<MainDTO> selectFeedList(MainDTO dto);

	//피드업로드
	public void uploadFeed(MainDTO dto);

	//showLevel 업데이트
	public void updateShowLevel(MainDTO dto);
}
