package bts.lab_lune;

import bts.lab_lune.presentation.PatientFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LabLuneApplication {
	public static void main(String[] args) {
		//SpringApplication.run(LabLuneApplication.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(LabLuneApplication.class).run(args);
		//Metodo para que Al momento de ejecutar Spring tambien ejecuta Fx
		Application.launch(PatientFx.class,args);
	}
}
