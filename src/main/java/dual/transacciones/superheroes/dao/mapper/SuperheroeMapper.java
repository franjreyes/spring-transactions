package dual.transacciones.superheroes.dao.mapper;

import dual.transacciones.superheroes.dao.modelo.Superheroe;

import java.util.List;

public interface SuperheroeMapper {
    int deleteByPrimaryKey(Integer superheroeId);

    int insert(Superheroe superheroe);

    Superheroe selectByPrimaryKey(Integer superheroeId);

    int updateByPrimaryKey(Superheroe superheroe);

    List<Superheroe> selectAll();
}