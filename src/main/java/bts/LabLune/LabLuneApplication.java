package bts.LabLune;

import bts.LabLune.presentacion.SistemaPacientFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabLuneApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LabLuneApplication.class, args);
		Application.launch(SistemaPacientFx.class,args);
		//Metodo para que Al momento de ejecutar Spring tambien ejecuta Fx
	}


}
