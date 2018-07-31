package monitor;

import java.util.List;

public class DataSummary {
	String url;
	List<DataPoint> entries;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<DataPoint> getEntries() {
		return entries;
	}
	public void setEntries(List<DataPoint> entries) {
		this.entries = entries;
	}

}
