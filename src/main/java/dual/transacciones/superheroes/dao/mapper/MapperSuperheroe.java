package dual.transacciones.superheroes.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dual.transacciones.superheroes.dao.modelo.Superheroe;

public class MapperSuperheroe implements RowMapper<Superheroe> {

    @Override
    public Superheroe mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Superheroe superheroe = new Superheroe();
    	superheroe.setIdentificador(rs.getLong("id"));
        superheroe.setNombre(rs.getString("nombre"));
        superheroe.setAlterego(rs.getString("alterego"));
        superheroe.setImagen(rs.getString("img"));

        return superheroe;
    }

}
