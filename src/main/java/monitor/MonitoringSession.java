package monitor;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitoringSession {
	private static final Logger logger = LoggerFactory.getLogger(MonitoringSession.class);
	private Status status;
	private String url;
	private int interval;
	private ArrayList<DataPoint> sessionData = new ArrayList<>();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> requestorHandle;
	@Autowired
	private RestCallService restCallService;
	
	public Status getStatus() {
		return status;
	}	
		
	public void setUrl(String url) {
		this.url = url;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void start() {
		status = Status.started;
		final Runnable requestor = new Runnable() {
		       public void run() {
		    	   DataPoint dp = restCallService.singleRequest(url);
		    	   sessionData.add(dp);
		    	   logger.info(dp.toString());		       
		       }
		     };
		requestorHandle = scheduler.scheduleAtFixedRate(requestor, 0, interval, TimeUnit.SECONDS);		
	}

	public void stop() {
		requestorHandle.cancel(true);
		status = Status.stopped;
		logger.info("Stopped monitoring " + url);
	}
	
	public ArrayList<DataPoint> getSessionData() {
		return sessionData;
	}
}
