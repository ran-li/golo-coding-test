package monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("prototype")
public class RestCallService {
	private static final Logger logger = LoggerFactory.getLogger(RestCallService.class);
	private final RestTemplate restTemplate;

	public RestCallService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public DataPoint singleRequest(String url) {
		MonitorResult res = null;
		
		try {
			res = restTemplate.getForObject(url, MonitorResult.class);
		} catch (Exception e) {
			res = new MonitorResult();
			res.setStatus(e.getMessage());
			return new DataPoint(res);
		}
		
		logger.info(res.toString());
		return new DataPoint(res);
	}
}
