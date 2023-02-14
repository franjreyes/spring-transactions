package dual.transacciones.superheroes.dao;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Superpoder;

public interface RepositorioSuperpoder {

	List<Superpoder> consultar(long identificadorHeroe);
	
	void crear(long identificadorHeroe, List<Superpoder> superpoderes);

	void eliminar(long identificadorHeroe);
}
