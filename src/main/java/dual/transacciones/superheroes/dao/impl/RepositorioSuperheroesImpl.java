package dual.transacciones.superheroes.dao.impl;

import java.util.List;

import dual.transacciones.superheroes.dao.RepositorioSuperheroes;
import dual.transacciones.superheroes.dao.mapper.MapperSuperheroe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dual.transacciones.superheroes.dao.modelo.Superheroe;

@Repository
public class RepositorioSuperheroesImpl implements RepositorioSuperheroes {

	@Autowired
	private JdbcTemplate template;
	
	public List<Superheroe> consultar() {
		try {
			return this.template.query("select * from superheroes", new MapperSuperheroe());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Superheroe consultar(long identificador) {
		try {
			return this.template.queryForObject("select * from superheroes where id = ?", 
					new MapperSuperheroe(), identificador);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void crear(Superheroe superheroe) {
		try {
			this.template.update("INSERT INTO SUPERHEROES VALUES(?,?,?,?)", 
					superheroe.getIdentificador(), 
					superheroe.getNombre(), 
					superheroe.getAlterego(), 
					superheroe.getImagen());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void modificar(Superheroe superheroe) {
		try {
			this.template.update("UPDATE SUPERHEROES SET NOMBRE = ?, "
					+ "ALTEREGO = ?, IMG = ? WHERE ID = ?", 
					superheroe.getNombre(), 
					superheroe.getAlterego(), 
					superheroe.getImagen(),
					superheroe.getIdentificador());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void eliminar(long identificador) {
		try {
			this.template.update("DELETE FROM SUPERHEROES WHERE ID = ?", 
					identificador);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
