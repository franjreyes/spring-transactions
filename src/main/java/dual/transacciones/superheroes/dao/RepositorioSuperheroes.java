package dual.transacciones.superheroes.dao;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Superheroe;

public interface RepositorioSuperheroes {

	List<Superheroe> consultar();
	
	Superheroe consultar(long identificador);
	
	void crear(Superheroe superheroe);

	void modificar(Superheroe superheroe);

	void eliminar(long identificador);

}
