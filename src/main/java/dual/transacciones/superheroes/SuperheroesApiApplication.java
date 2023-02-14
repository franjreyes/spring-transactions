package dual.transacciones.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("dual.transacciones.superheroes.dao.mapper")
public class SuperheroesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApiApplication.class, args);
	}

}
