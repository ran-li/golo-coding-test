package monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
@Configuration
public class MonitorController {

	private ConcurrentHashMap<String, MonitoringSession> sessions = new ConcurrentHashMap<>();

    @RequestMapping("/start")
    public String startMonitoring(
    		@RequestParam("interval") int interval, 
    		@RequestParam("url") String url) {
    	//todo: this is not thread safe...
    	if (sessions.containsKey(url)) {
    		return "url: " + url + " is already being monitored!";
    	} else {
    		MonitoringSession sesh = createSession();
    		sesh.setInterval(interval);
    		sesh.setUrl(url);
    		sesh.start();
    		sessions.put(url, sesh);
    	}
        return "Monitoring successfully started at " + url
        		+ "; monitor data is collected every " + interval + " seconds.";
    }
    
    @RequestMapping("/stop")
    public String stoptMonitoring(@RequestParam("url") String url) {
    	if (sessions.containsKey(url)) {
        	sessions.get(url).stop();
        	sessions.remove(url);
            return "Stopped monitoring " + url;
    	} else {
    		return "Cannot stop a url that hasn't been started!";
    	}
    }    
    
    @RequestMapping("/summary")
    public Map<String, List<DataPoint>> reportSummary() {
    	//todo: thread safety
    	Map<String, List<DataPoint>> result = new HashMap<>();
    	for (String url : sessions.keySet()) {
    		result.put(url, sessions.get(url).getSessionData());
    	}    	
		return result;
    }
        
    @Bean
    @Scope("prototype")
    public MonitoringSession createSession() {
        return new MonitoringSession();
    }
}
