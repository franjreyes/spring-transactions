package dual.transacciones.superheroes.dao;

import dual.transacciones.superheroes.dao.modelo.Superpoder;

import java.util.List;

public interface RepositorioSuperpoder {

	List<Superpoder> consultar(Integer superheroeId);
	
	void crear(Integer superheroeId, List<Superpoder> superpoderes);

	void eliminar(Integer superheroeId);
}
