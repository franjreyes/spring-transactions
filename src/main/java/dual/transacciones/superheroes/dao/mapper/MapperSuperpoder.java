package dual.transacciones.superheroes.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import dual.transacciones.superheroes.dao.modelo.Superpoder;

public class MapperSuperpoder implements RowMapper<Superpoder> {

    @Override
    public Superpoder mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Superpoder superpoder = new Superpoder();
    	superpoder.setIdentificador(rs.getLong("id"));
    	superpoder.setPoder(rs.getString("poder"));

        return superpoder;
    }

}
