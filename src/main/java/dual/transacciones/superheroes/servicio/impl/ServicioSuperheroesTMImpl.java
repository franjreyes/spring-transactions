package dual.transacciones.superheroes.servicio.impl;

import dual.transacciones.superheroes.dao.mapper.SuperheroeMapper;
import dual.transacciones.superheroes.dao.modelo.Debilidad;
import dual.transacciones.superheroes.dao.modelo.Superheroe;
import dual.transacciones.superheroes.dao.modelo.Superpoder;
import dual.transacciones.superheroes.excepciones.ImagenException;
import dual.transacciones.superheroes.excepciones.SuperheroeException;
import dual.transacciones.superheroes.servicio.ServicioDebilidades;
import dual.transacciones.superheroes.servicio.ServicioSuperheroes;
import dual.transacciones.superheroes.servicio.ServicioSuperpoder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Transactional(rollbackFor = SuperheroeException.class, noRollbackFor = ImagenException.class)
public class ServicioSuperheroesTMImpl implements ServicioSuperheroes {

    private final PlatformTransactionManager transactionManager;
    private final SuperheroeMapper mapper;
    private final ServicioSuperpoder servicioSuperpoder;
    private final ServicioDebilidades servicioDebilidades;

    public ServicioSuperheroesTMImpl(PlatformTransactionManager transactionManager,
                                     SuperheroeMapper mapper,
                                     ServicioSuperpoder servicioSuperpoder,
                                     ServicioDebilidades servicioDebilidades) {
        this.transactionManager = transactionManager;
        this.mapper = mapper;
        this.servicioSuperpoder = servicioSuperpoder;
        this.servicioDebilidades = servicioDebilidades;
    }

    @Override
    public List<Superheroe> consultar() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        List<Superheroe> superheroes = transactionTemplate.execute(txStatus -> this.mapper.selectAll());
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
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            Superheroe superheroe = transactionTemplate.execute(txStatus -> this.mapper.selectByPrimaryKey(superheroeId));
            superheroe.setSuperpoderes(this.consutarSuperpoderes(superheroeId));
            superheroe.setDebilidades(this.consutarDebilidades(superheroeId));

            return superheroe;

        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("No se ha encontrado el superhéroe "
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
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        try {
            Superheroe superheroeExiste = transactionTemplate.execute(txStatus -> this.mapper.selectByPrimaryKey(superheroe.getId()));
            if (superheroeExiste != null) {
                throw new SuperheroeException("Ya existe un superhéroe con "
                        + "el identificador " + superheroe.getId());
            }
        } catch (EmptyResultDataAccessException e) {
            ;
        }

        transactionTemplate.execute(txStatus -> {
            this.mapper.insert(superheroe);
            return null;
        });

        if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
            throw new ImagenException();
        }
    }

    @Override
    public void modificar(Superheroe superheroe) throws SuperheroeException {
        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.execute(txStatus -> {
                this.mapper.selectByPrimaryKey(superheroe.getId());
                this.mapper.updateByPrimaryKey(superheroe);
                this.servicioSuperpoder.quitarSuperpoderASuperheroe(superheroe.getId());
                return null;
            });
            
            this.crearSuperpoderes(superheroe);
            
            transactionTemplate.execute(txStatus -> {
                this.servicioDebilidades.quitarDebilidadASuperheroe(superheroe.getId());
                return null;
            });
            
            this.crearDebilidades(superheroe);

            if (superheroe.getImagen() == null || superheroe.getImagen().length() == 0) {
                throw new ImagenException();
            }

        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superhéroe con identificador "
                    + superheroe.getId() + " no existe.");
        }
    }

    private void crearSuperpoderes(Superheroe superheroe) throws SuperheroeException {
        if (superheroe.getSuperpoderes() == null
                || superheroe.getSuperpoderes().isEmpty()) {
            throw new SuperheroeException("El superhéroe necesita al menos un superpoder");
        }

        this.servicioSuperpoder.addSuperpoderASuperheroe(superheroe.getId(),
                superheroe.getSuperpoderes());
    }

    private void crearDebilidades(Superheroe superheroe) throws SuperheroeException {
        if (superheroe.getDebilidades() == null
                || superheroe.getDebilidades().isEmpty()) {
            throw new SuperheroeException("El superhéroe necesita al menos una debilidad");
        }

        this.servicioDebilidades.addDebilidadASupuerheroe(superheroe.getId(),
                superheroe.getDebilidades());
    }

    @Override
    public void eliminar(Integer superheroeId) throws SuperheroeException {
        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.execute(txStatus -> {
                this.mapper.selectByPrimaryKey(superheroeId);
                this.servicioSuperpoder.quitarSuperpoderASuperheroe(superheroeId);
                this.servicioDebilidades.quitarDebilidadASuperheroe(superheroeId);
                this.mapper.deleteByPrimaryKey(superheroeId);
                return null;
            });
        } catch (EmptyResultDataAccessException e) {
            throw new SuperheroeException("El superhéroe con identificador "
                    + superheroeId + " no existe.");
        }
    }
}
