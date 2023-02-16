package dual.transacciones.superheroes.servicio.impl;

import dual.transacciones.superheroes.dao.SuperheroesDao;
import dual.transacciones.superheroes.dao.modelo.Superheroe;
import dual.transacciones.superheroes.excepciones.ImagenException;
import dual.transacciones.superheroes.excepciones.SuperheroeException;
import dual.transacciones.superheroes.servicio.ServicioSuperheroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Transactional(rollbackFor = SuperheroeException.class, noRollbackFor = ImagenException.class)
public class ServicioSuperheroesImpl implements ServicioSuperheroes {

    @Autowired
    private SuperheroesDao superheroesDao;

    public List<Superheroe> consultar() {
        List<Superheroe> superheroes = this.superheroesDao.findAll();
        if (superheroes.isEmpty()) {
            return superheroes;
        }

        return superheroes;
    }

    @Override
    public Superheroe consultar(Integer superheroeId) throws SuperheroeException {

        Optional<Superheroe> resultado = this.superheroesDao.findById(superheroeId);

        if (resultado.isPresent())
            return resultado.get();

        throw new SuperheroeException("No se ha encontrado el superhéroe con identificador " + superheroeId);
    }

    @Override
    public void crear(Superheroe superheroe) throws SuperheroeException {
        try {
            Optional<Superheroe> resultado = this.superheroesDao.findById(superheroe.getId());
            if (resultado.isPresent()) {
                throw new SuperheroeException("Ya existe un superhéroe con "
                        + "el identificador " + superheroe.getId());
            }
        } catch (EmptyResultDataAccessException e) {}

        this.superheroesDao.save(superheroe);

        if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
            throw new ImagenException();
        }
    }

    @Override
    public void modificar(Superheroe superheroe) throws SuperheroeException {
        try {
            this.consultar(superheroe.getId());
            this.superheroesDao.save(superheroe);

            if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
                throw new ImagenException();
            }

        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superhéroe con identificador "
                    + superheroe.getId() + " no existe.");
        }
    }

    @Override
    public void eliminar(Integer superheroeId) throws SuperheroeException {
        try {
            this.superheroesDao.delete(this.consultar(superheroeId));
        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superhéroe con identificador "
                    + superheroeId + " no existe.");
        }
    }
}
