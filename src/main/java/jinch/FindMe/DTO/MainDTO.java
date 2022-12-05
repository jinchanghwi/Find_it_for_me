package jinch.FindMe.DTO;

public class MainDTO {

	private String feedId; //피드ID
	private String writerId;	//작성자ID
	private String text;	//텍스트
	private String image;	//이미지
	private String showYN;	//화면표시여부
	private int showLevel;	//화면표시순서
	private String answer;	//정답



	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getShowYN() {
		return showYN;
	}

	public void setShowYN(String showYN) {
		this.showYN = showYN;
	}

	public int getShowLevel() {
		return showLevel;
	}

	public void setShowLevel(int showLevel) {
		this.showLevel = showLevel;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
}