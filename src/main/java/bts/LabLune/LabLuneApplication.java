package bts.LabLune;

import bts.LabLune.presentacion.SistemaPacientFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LabLuneApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LabLuneApplication.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(LabLuneApplication.class)
				.run(args);
		Application.launch(SistemaPacientFx.class,args);
		//Metodo para que Al momento de ejecutar Spring tambien ejecuta Fx
	}


}
