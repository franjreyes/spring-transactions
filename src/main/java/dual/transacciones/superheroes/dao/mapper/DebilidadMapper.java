package dual.transacciones.superheroes.dao.mapper;

import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.dao.modelo.SuperheroeDebilidad;

import java.util.List;

public interface DebilidadMapper {

    List<Debilidad> getBySuperheroeId(Integer superheroeId);

    int insertSuperheroeDebilidades(List<SuperheroeDebilidad> superheroeDebilidades);

    int deleteBySuperheroeId(Integer superheroeId);
}