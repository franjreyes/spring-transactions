package dual.transacciones.superheroes.servicio;

import dual.transacciones.superheroes.dao.modelo.Superpoder;

import java.util.List;

public interface ServicioSuperpoder {

	List<Superpoder> consultarPorSuperheroeId(Integer superheroeId);
	
	void addSuperpoderASuperheroe(Integer superheroeId, List<Superpoder> superpoderes);

	void quitarSuperpoderASuperheroe(Integer superheroeId);

}
