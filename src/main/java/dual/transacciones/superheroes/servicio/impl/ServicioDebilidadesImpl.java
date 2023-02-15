package dual.transacciones.superheroes.servicio.impl;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.servicio.ServicioDebilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dual.transacciones.superheroes.dao.RepositorioDebilidades;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ServicioDebilidadesImpl implements ServicioDebilidades {

	@Autowired
	private RepositorioDebilidades repositorio;
	
	@Override
	public List<Debilidad> consultarPorSuperheroeId(Integer superheroeId) {
		return this.repositorio.getBySuperheroeId(superheroeId);
	}

	@Override
	public void addDebilidadASupuerheroe(Integer superheroeId, List<Debilidad> debilidades) {
		this.repositorio.create(superheroeId, debilidades);
	}

	@Override
	public void quitarDebilidadASuperheroe(Integer superheroeId) {
		this.repositorio.delete(superheroeId);
	}

	
}
