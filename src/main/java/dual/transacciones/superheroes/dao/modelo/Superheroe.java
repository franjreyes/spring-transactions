package dual.transacciones.superheroes.dao.modelo;

import java.util.List;

public class Superheroe {

    private Integer id;

    private String nombre;

    private String alterego;

    private String imagen;

    private List<Debilidad> debilidades;

    private List<Superpoder> superpoderes;
    
    public Superheroe(Integer id, String nombre, String alterego, String imagen, List<Debilidad> debilidades,
			List<Superpoder> superpoderes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alterego = alterego;
		this.imagen = imagen;
		this.debilidades = debilidades;
		this.superpoderes = superpoderes;
	}

	public Superheroe(Integer id, String nombre, String alterego, String imagen) {
        this(id,nombre,alterego,imagen,null,null);
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlterego() {
		return alterego;
	}

	public void setAlterego(String alterego) {
		this.alterego = alterego;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Debilidad> getDebilidades() {
		return debilidades;
	}

	public void setDebilidades(List<Debilidad> debilidades) {
		this.debilidades = debilidades;
	}

	public List<Superpoder> getSuperpoderes() {
		return superpoderes;
	}

	public void setSuperpoderes(List<Superpoder> superpoderes) {
		this.superpoderes = superpoderes;
	}
}