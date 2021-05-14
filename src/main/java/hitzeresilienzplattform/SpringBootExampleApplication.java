package hitzeresilienzplattform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExampleApplication {

	public static void main(String[] args) throws MqttException{
		MqttClient client = new MqttClient("tcp://compress.seelab.fh-dortmund.de:1883", MqttClient.generateClientId());
		MqttConnectOptions opt = new MqttConnectOptions();
		opt.setUserName("swt2");
		opt.setPassword("sose2021".toCharArray());

		client.connect(opt);

		client.disconnect();
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}
}