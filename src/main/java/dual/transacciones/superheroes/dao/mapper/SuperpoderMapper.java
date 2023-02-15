package dual.transacciones.superheroes.dao.mapper;

import dual.transacciones.superheroes.dao.modelo.SuperheroeSuperpoder;
import dual.transacciones.superheroes.dao.modelo.Superpoder;

import java.util.List;

public interface SuperpoderMapper {

    List<Superpoder> getBySuperheroeId(Integer superheroeId);

    int insertSuperheroeSuperpoderes(List<SuperheroeSuperpoder> superheroeSuperpoderes);

    int deleteBySuperheroeId(Integer superheroeId);
}