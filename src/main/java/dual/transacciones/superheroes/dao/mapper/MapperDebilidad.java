package dual.transacciones.superheroes.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dual.transacciones.superheroes.dao.modelo.Debilidad;

public class MapperDebilidad implements RowMapper<Debilidad> {

    @Override
    public Debilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Debilidad debilidad = new Debilidad();
    	debilidad.setIdentificador(rs.getLong("id"));
    	debilidad.setDebilidad(rs.getString("debilidad"));

        return debilidad;
    }

}
