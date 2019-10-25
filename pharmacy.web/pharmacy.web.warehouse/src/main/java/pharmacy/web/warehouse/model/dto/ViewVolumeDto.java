package pharmacy.web.warehouse.model.dto;

public class ViewVolumeDto {

	private String tvCount;
	private String tvtime;
	public ViewVolumeDto(String tvCount, String tvtime) {
		super();
		this.tvCount = tvCount;
		this.tvtime = tvtime;
	}
	public ViewVolumeDto() {
		super();
	}
	public String getTvCount() {
		return tvCount;
	}
	public void setTvCount(String tvCount) {
		this.tvCount = tvCount;
	}
	public String getTvtime() {
		return tvtime;
	}
	public void setTvtime(String tvtime) {
		this.tvtime = tvtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ViewVolumeDto [tvCount=");
		builder.append(tvCount);
		builder.append(", tvtime=");
		builder.append(tvtime);
		builder.append("]");
		return builder.toString();
	}
	
	
}
