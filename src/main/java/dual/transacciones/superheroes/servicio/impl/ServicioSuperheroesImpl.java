package dual.transacciones.superheroes.servicio.impl;

import dual.transacciones.superheroes.dao.RepositorioSuperheroes;
import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.dao.modelo.Superheroe;
import dual.transacciones.superheroes.dao.modelo.Superpoder;
import dual.transacciones.superheroes.excepciones.ImagenException;
import dual.transacciones.superheroes.excepciones.SuperheroeException;
import dual.transacciones.superheroes.servicio.ServicioDebilidades;
import dual.transacciones.superheroes.servicio.ServicioSuperheroes;
import dual.transacciones.superheroes.servicio.ServicioSuperpoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Transactional(rollbackFor = SuperheroeException.class, noRollbackFor = ImagenException.class)
public class ServicioSuperheroesImpl implements ServicioSuperheroes {

    @Autowired
    private RepositorioSuperheroes repositorio;

    @Autowired
    private ServicioSuperpoder servicioSuperpoder;

    @Autowired
    private ServicioDebilidades servicioDebilidades;

    public List<Superheroe> consultar() {
        List<Superheroe> superheroes = this.repositorio.consultar();
        if (superheroes.isEmpty()) {
            return superheroes;
        }

        superheroes.stream().forEach(superheroe -> {
            superheroe.setSuperpoderes(this.consutarSuperpoderes(superheroe.getId()));
            superheroe.setDebilidades(this.consutarDebilidades(superheroe.getId()));
        });

        return superheroes;
    }

    @Override
    public Superheroe consultar(Integer superheroeId) throws SuperheroeException {
        try {
            Superheroe superheroe = this.repositorio.consultar(superheroeId);
            superheroe.setSuperpoderes(this.consutarSuperpoderes(superheroeId));
            superheroe.setDebilidades(this.consutarDebilidades(superheroeId));

            return superheroe;

        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("No se ha encontrado el superh??roe "
                    + "con identificador " + superheroeId);
        }
    }

    private List<Superpoder> consutarSuperpoderes(Integer superheroeId) {
        return this.servicioSuperpoder.consultarPorSuperheroeId(superheroeId);
    }

    private List<Debilidad> consutarDebilidades(Integer superheroeId) {
        return this.servicioDebilidades.consultarPorSuperheroeId(superheroeId);
    }

    @Override
    public void crear(Superheroe superheroe) throws SuperheroeException {
        try {
            Superheroe superheroeExiste = this.repositorio.
                    consultar(superheroe.getId());
            if (superheroeExiste != null) {
                throw new SuperheroeException("Ya existe un superh??roe con "
                        + "el identificador " + superheroe.getId());
            }
        } catch (EmptyResultDataAccessException e) {
            ;
        }

        this.repositorio.crear(superheroe);

        if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
            throw new ImagenException();
        }
    }

    @Override
    public void modificar(Superheroe superheroe) throws SuperheroeException {
        try {
            this.repositorio.consultar(superheroe.getId());
            this.repositorio.modificar(superheroe);
            this.servicioSuperpoder.quitarSuperpoderASuperheroe(superheroe.getId());
            this.addSuperpoderes(superheroe);
            this.servicioDebilidades.quitarDebilidadASuperheroe(superheroe.getId());
            this.addDebilidades(superheroe);

            if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
                throw new ImagenException();
            }

        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superh??roe con identificador "
                    + superheroe.getId() + " no existe.");
        }
    }

    private void addSuperpoderes(Superheroe superheroe) throws SuperheroeException {
        if (superheroe.getSuperpoderes() == null
                || superheroe.getSuperpoderes().isEmpty()) {
            throw new SuperheroeException("El superh??roe necesita al menos un superpoder");
        }

        this.servicioSuperpoder.addSuperpoderASuperheroe(superheroe.getId(),
                superheroe.getSuperpoderes());
    }

    private void addDebilidades(Superheroe superheroe) throws SuperheroeException {
        if (superheroe.getDebilidades() == null
                || superheroe.getDebilidades().isEmpty()) {
            throw new SuperheroeException("El superh??roe necesita al menos una debilidad");
        }

        this.servicioDebilidades.addDebilidadASupuerheroe(superheroe.getId(),
                superheroe.getDebilidades());
    }

    @Override
    public void eliminar(Integer superheroeId) throws SuperheroeException {
        try {
            this.repositorio.consultar(superheroeId);
            this.servicioSuperpoder.quitarSuperpoderASuperheroe(superheroeId);
            this.servicioDebilidades.quitarDebilidadASuperheroe(superheroeId);
            this.repositorio.eliminar(superheroeId);
        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superh??roe con identificador "
                    + superheroeId + " no existe.");
        }
    }
}
