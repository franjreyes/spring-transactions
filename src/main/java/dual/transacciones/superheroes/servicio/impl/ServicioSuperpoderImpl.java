package dual.transacciones.superheroes.servicio.impl;

import java.util.List;

import dual.transacciones.superheroes.servicio.ServicioSuperpoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dual.transacciones.superheroes.dao.modelo.Superpoder;
import dual.transacciones.superheroes.dao.RepositorioSuperpoder;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ServicioSuperpoderImpl implements ServicioSuperpoder {

	@Autowired
	private RepositorioSuperpoder repositorio;
	
	@Override
	public List<Superpoder> consultar(long identificadorHeroe) {
		return this.repositorio.consultar(identificadorHeroe);
	}

	@Override
	public void crear(long identificadorHeroe, List<Superpoder> superpoderes) {
		this.repositorio.crear(identificadorHeroe, superpoderes);
	}

	@Override
	public void eliminar(long identificadorHeroe) {
		this.repositorio.eliminar(identificadorHeroe);
	}

	
}
