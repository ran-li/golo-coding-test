package monitor;

import java.time.LocalDateTime;

public class DataPoint {
	private LocalDateTime timeStamp;
	private MonitorResult availability;
	
	public DataPoint (LocalDateTime t, MonitorResult res) {
		timeStamp = t;
		availability = res;
	}
	
	public DataPoint (MonitorResult result) {
		timeStamp = LocalDateTime.now();
		availability = result;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public MonitorResult getAvailability() {
		return availability;
	}
	
	public void setAvailability(MonitorResult availability) {
		this.availability = availability;
	}
	
    @Override
    public String toString() {
        return "DataPoint {timeStamp: " + timeStamp + ", "
        		+ "availability: {" + availability + "}}";
    }
}
