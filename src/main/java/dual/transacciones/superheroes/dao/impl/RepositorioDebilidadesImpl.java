package dual.transacciones.superheroes.dao.impl;

import dual.transacciones.superheroes.dao.RepositorioDebilidades;
import dual.transacciones.superheroes.dao.mapper.DebilidadMapper;
import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.dao.modelo.SuperheroeDebilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioDebilidadesImpl implements RepositorioDebilidades {

    private final DebilidadMapper mapper;

    @Autowired
    public RepositorioDebilidadesImpl(DebilidadMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Debilidad> getBySuperheroeId(Integer superheroeId) {
        try {
            return this.mapper.getBySuperheroeId(superheroeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void create(Integer superheroeId, List<Debilidad> debilidades) {
        try {
			List<SuperheroeDebilidad> superheroeDebilidades = debilidades.stream()
							.map(debilidad -> new SuperheroeDebilidad(superheroeId, debilidad.getId()))
                    .collect(Collectors.toList());
			this.mapper.insertSuperheroeDebilidades(superheroeDebilidades);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(Integer superheroeId) {
        try {
            this.mapper.deleteBySuperheroeId(superheroeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
