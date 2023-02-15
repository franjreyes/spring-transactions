package dual.transacciones.superheroes.dao.impl;

import java.util.List;

import dual.transacciones.superheroes.dao.RepositorioSuperheroes;
import dual.transacciones.superheroes.dao.mapper.SuperheroeMapper;
import dual.transacciones.superheroes.dao.modelo.Superheroe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioSuperheroesImpl implements RepositorioSuperheroes {

	private final SuperheroeMapper mapper;

	@Autowired
	public RepositorioSuperheroesImpl(SuperheroeMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<Superheroe> consultar() {
		try {
			return this.mapper.selectAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Superheroe consultar(Integer superheroeId) {
		try {
			return this.mapper.selectByPrimaryKey(superheroeId);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void crear(Superheroe superheroe) {
		try {
			this.mapper.insert(superheroe);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void modificar(Superheroe superheroe) {
		try {
			this.mapper.updateByPrimaryKey(superheroe);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void eliminar(Integer superheroeId) {
		try {
			this.mapper.deleteByPrimaryKey(superheroeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
