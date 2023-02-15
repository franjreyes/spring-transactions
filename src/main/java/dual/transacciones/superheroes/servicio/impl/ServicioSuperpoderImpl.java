package dual.transacciones.superheroes.servicio.impl;

import java.util.List;

import dual.transacciones.superheroes.dao.modelo.Superpoder;
import dual.transacciones.superheroes.servicio.ServicioSuperpoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dual.transacciones.superheroes.dao.RepositorioSuperpoder;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ServicioSuperpoderImpl implements ServicioSuperpoder {

	@Autowired
	private RepositorioSuperpoder repositorio;
	
	@Override
	public List<Superpoder> consultarPorSuperheroeId(Integer superheroeId) {
		return this.repositorio.consultar(superheroeId);
	}

	@Override
	public void addSuperpoderASuperheroe(Integer superheroeId, List<Superpoder> superpoderes) {
		this.repositorio.crear(superheroeId, superpoderes);
	}

	@Override
	public void quitarSuperpoderASuperheroe(Integer superheroeId) {
		this.repositorio.eliminar(superheroeId);
	}

	
}
