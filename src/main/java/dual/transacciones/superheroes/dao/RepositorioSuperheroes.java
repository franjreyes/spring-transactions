package dual.transacciones.superheroes.dao;

import dual.transacciones.superheroes.dao.modelo.Superheroe;

import java.util.List;

public interface RepositorioSuperheroes {

	List<Superheroe> consultar();
	
	Superheroe consultar(Integer superheroeId);
	
	void crear(Superheroe superheroe);

	void modificar(Superheroe superheroe);

	void eliminar(Integer superheroeId);

}
