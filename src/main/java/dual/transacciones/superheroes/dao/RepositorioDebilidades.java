package dual.transacciones.superheroes.dao;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Debilidad;

public interface RepositorioDebilidades {

	List<Debilidad> consultar(long identificadorHeroe);
	
	void crear(long identificadorHeroe, List<Debilidad> superpoderes);

	void eliminar(long identificadorHeroe);
}
