package dual.transacciones.superheroes.dao;

import dual.transacciones.superheroes.dao.modelo.Debilidad;

import java.util.List;

public interface RepositorioDebilidades {

	List<Debilidad> getBySuperheroeId(Integer superheroeId);

    void create(Integer superheroeId, List<Debilidad> debilidad);

    void delete(Integer superheroeId);
}
