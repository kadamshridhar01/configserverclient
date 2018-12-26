# configserverclient
How to read the property value from Spring Config Server

#BLock Diagram 

Config Client--> JVM argument set Env value  ---->Config server ---------->Git
^
|
|
post request 

Spring load the all property 1 time only .After check in property file git .it will not pick up the new changes.To pick up the new changes we need make one more post request.

post -->http://localhost:${server.port}/${spring.application.name}/actuator/refresh

Above  request reload the property

#JVM argument to read the property files from which Enviorment
-Dspring.profiles.active=prod

If above property default value is "default".To know property files naming convension ,check the SpringConfig-Dev project


#Controller code to fetch proprty value .

@RestController

public class ReadPropertyController {
	
	@Value("${spring.datasource.url: url not found}")
	private String configMessage;
	
	
	@GetMapping("/${server.contextPath}")
	public String jdbcUrl() {
		return configMessage;
	}

}

#Application file changes
server.port=8889

--Making sure not overlapping the port hence defing port which application run 

spring.application.name =configserverclient

--project name optional 

spring.cloud.config.uri=http://localhost:8888

--config server url manadatory property .to know which config server we need to hit 

Above 1 proprty enough to read the values from Config server 

management.security.enabled=false 

--to enable enpoint like Env and easy to test (optional) 

-------------------
As we mention to refresh changes .we need to hit post request .
following are the changes we need to make 

application.properties

management.endpoints.web.exposure.include=*

In Controller add the refreshScope annotation 


@RefreshScope
@RestController

public class ReadPropertyController {
	
	@Value("${spring.datasource.url: url not found}")
	private String configMessage;
	
	
	@GetMapping("/${server.contextPath}")
	public String jdbcUrl() {
		return configMessage;
	}

}
post --http://localhost:8889/actuator/refresh/

it will reload the files from config server
