package dual.transacciones.superheroes.dao.modelo;

import java.util.List;

public class Superheroe {

	private long identificador;
	
	private String nombre;
	
	private String alterego;
	
	private String imagen;
	
	private List<Superpoder> superpoderes;
	
	private List<Debilidad> debilidades;

	public long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(long identificador) {
		this.identificador = identificador;
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

	public List<Superpoder> getSuperpoderes() {
		return superpoderes;
	}

	public void setSuperpoderes(List<Superpoder> superpoderes) {
		this.superpoderes = superpoderes;
	}

	public List<Debilidad> getDebilidades() {
		return debilidades;
	}

	public void setDebilidades(List<Debilidad> debilidades) {
		this.debilidades = debilidades;
	}
	
}
