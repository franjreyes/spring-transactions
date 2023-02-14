package dual.transacciones.superheroes.servicio;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Superpoder;

public interface ServicioSuperpoder {

	List<Superpoder> consultar(long identificadorHeroe);
	
	void crear(long identificadorHeroe, List<Superpoder> debilidades);

	void eliminar(long identificadorHeroe);

}
