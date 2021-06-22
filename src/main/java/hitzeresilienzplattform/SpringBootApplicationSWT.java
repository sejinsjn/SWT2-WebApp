package hitzeresilienzplattform;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApplicationSWT{

	public static void main(String[] args) throws MqttException {

		System.out.println("== START HITZE-RESILIENZ PLATTFORM ==");
		SpringApplication.run(SpringBootApplicationSWT.class, args);

	}

}