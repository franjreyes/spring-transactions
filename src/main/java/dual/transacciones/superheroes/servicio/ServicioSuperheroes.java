package dual.transacciones.superheroes.servicio;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Superheroe;
import dual.transacciones.superheroes.excepciones.SuperheroeException;

public interface ServicioSuperheroes {

	List<Superheroe> consultar();
	
	Superheroe consultar(long identificador) throws SuperheroeException;
	
	void crear(Superheroe superheroe) throws SuperheroeException;

	void modificar(Superheroe superheroe) throws SuperheroeException;

	void eliminar(long identificador) throws SuperheroeException;

}
