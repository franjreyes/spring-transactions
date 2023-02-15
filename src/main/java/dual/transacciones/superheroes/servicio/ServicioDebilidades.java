package dual.transacciones.superheroes.servicio;

import dual.transacciones.superheroes.dao.modelo.Debilidad;

import java.util.List;

public interface ServicioDebilidades {

	List<Debilidad> consultarPorSuperheroeId(Integer superheroeId);
	
	void addDebilidadASupuerheroe(Integer superheroeId, List<Debilidad> debilidades);

	void quitarDebilidadASuperheroe(Integer superheroeId);

}
