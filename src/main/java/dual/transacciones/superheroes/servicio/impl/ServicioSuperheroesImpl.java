package dual.transacciones.superheroes.servicio.impl;

import java.util.List;

import dual.transacciones.superheroes.excepciones.ImagenException;
import dual.transacciones.superheroes.excepciones.SuperheroeException;
import dual.transacciones.superheroes.servicio.ServicioDebilidades;
import dual.transacciones.superheroes.servicio.ServicioSuperheroes;
import dual.transacciones.superheroes.servicio.ServicioSuperpoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.dao.modelo.Superheroe;
import dual.transacciones.superheroes.dao.modelo.Superpoder;
import dual.transacciones.superheroes.dao.RepositorioSuperheroes;

@Service
@Transactional(rollbackFor = SuperheroeException.class, noRollbackFor = ImagenException.class)
public class ServicioSuperheroesImpl implements ServicioSuperheroes {

	@Autowired
	private RepositorioSuperheroes repositorio;
	
	@Autowired
	private ServicioSuperpoder servicioSuperpoder;

	@Autowired
	private ServicioDebilidades servicioDebilidades;
	
	public List<Superheroe> consultar(){
		List<Superheroe> superheroes = this.repositorio.consultar();
		if(superheroes.isEmpty()) {
			return superheroes;
		}
		
		superheroes.stream().forEach(superheroe -> {
			superheroe.setSuperpoderes(this.consutarSuperpoderes(superheroe.getIdentificador()));
			superheroe.setDebilidades(this.consutarDebilidades(superheroe.getIdentificador()));
		});

		return superheroes;
	}

	@Override
	public Superheroe consultar(long identificador) throws SuperheroeException {
		try {
			Superheroe superheroe = this.repositorio.consultar(identificador);
			superheroe.setSuperpoderes(this.consutarSuperpoderes(identificador));
			superheroe.setDebilidades(this.consutarDebilidades(identificador));

			return superheroe;
			
		} catch (EmptyResultDataAccessException e) {
			throw new SuperheroeException("No se ha encontrado el superhéroe "
					+ "con identificador " + identificador);
		}
	}
	
	private List<Superpoder> consutarSuperpoderes(long identificador){
		return this.servicioSuperpoder.consultar(identificador);
	}

	private List<Debilidad> consutarDebilidades(long identificador){
		return this.servicioDebilidades.consultar(identificador);
	}

	@Override
	public void crear(Superheroe superheroe) throws SuperheroeException {
		try {
			Superheroe superheroeExiste = this.repositorio.
					consultar(superheroe.getIdentificador());
			if(superheroeExiste != null) {
				throw new SuperheroeException("Ya existe un superhéroe con "
						+ "el identificador " + superheroe.getIdentificador());
			}
		} catch (EmptyResultDataAccessException e) {;}
		
		this.repositorio.crear(superheroe);
		this.crearSuperpoderes(superheroe);
		this.crearDebilidades(superheroe);

		if(superheroe.getImagen() == null || superheroe.getImagen().length() == 0){
			throw new ImagenException();
		}
	}

	@Override
	public void modificar(Superheroe superheroe) throws SuperheroeException {
		try {
			this.repositorio.consultar(superheroe.getIdentificador());
			this.repositorio.modificar(superheroe);
			this.servicioSuperpoder.eliminar(superheroe.getIdentificador());
			this.crearSuperpoderes(superheroe);
			this.servicioDebilidades.eliminar(superheroe.getIdentificador());
			this.crearDebilidades(superheroe);
			
			if(superheroe.getImagen() == null || superheroe.getImagen().length() == 0){
				throw new ImagenException();
			}

		} catch (EmptyResultDataAccessException e) {
			throw new SuperheroeException("El superhéroe con identificador " 
						+ superheroe.getIdentificador() + " no existe.");
		}
	}
	
	private void crearSuperpoderes(Superheroe superheroe) throws SuperheroeException {
		if(superheroe.getSuperpoderes() == null 
				||  superheroe.getSuperpoderes().isEmpty()) {
			throw new SuperheroeException("El superhéroe necesita al menos un superpoder");
		}
		
		this.servicioSuperpoder.crear(superheroe.getIdentificador(), 
				superheroe.getSuperpoderes());
	}

	private void crearDebilidades(Superheroe superheroe) throws SuperheroeException {
		if(superheroe.getDebilidades() == null 
				||  superheroe.getDebilidades().isEmpty()) {
			throw new SuperheroeException("El superhéroe necesita al menos una debilidad");
		}

		this.servicioDebilidades.crear(superheroe.getIdentificador(), 
				superheroe.getDebilidades());
	}

	@Override
	public void eliminar(long identificador) throws SuperheroeException {
		try {
			this.repositorio.consultar(identificador);
			this.servicioSuperpoder.eliminar(identificador);
			this.servicioDebilidades.eliminar(identificador);
			this.repositorio.eliminar(identificador);
		} catch (EmptyResultDataAccessException e) {
			throw new SuperheroeException("El superhéroe con identificador " 
					+ identificador + " no existe.");
		}
	}
}
