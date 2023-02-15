package dual.transacciones.superheroes.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import dual.transacciones.superheroes.dao.RepositorioSuperpoder;
import dual.transacciones.superheroes.dao.mapper.SuperpoderMapper;
import dual.transacciones.superheroes.dao.modelo.SuperheroeSuperpoder;
import dual.transacciones.superheroes.dao.modelo.Superpoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioSuperpoderImpl implements RepositorioSuperpoder {

	private final SuperpoderMapper mapper;

	@Autowired
	public RepositorioSuperpoderImpl(SuperpoderMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Superpoder> consultar(Integer superheroeId) {
		try {
			return this.mapper.getBySuperheroeId(superheroeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void crear(Integer superheroeId, List<Superpoder> superpoderes) {
		try {
			List<SuperheroeSuperpoder> superheroeSuperpoderes = superpoderes
					.stream()
					.map(superpoder -> new SuperheroeSuperpoder(superheroeId, superpoder.getId()))
					.collect(Collectors.toList());
			this.mapper.insertSuperheroeSuperpoderes(superheroeSuperpoderes);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void eliminar(Integer superheroeId) {
		try {
			this.mapper.deleteBySuperheroeId(superheroeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
