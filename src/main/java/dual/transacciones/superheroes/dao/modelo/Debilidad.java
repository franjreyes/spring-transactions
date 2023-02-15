package dual.transacciones.superheroes.dao.modelo;

public class Debilidad {

    private Integer id;

    private String debilidad;

	public Debilidad(Integer id, String debilidad) {
		super();
		this.id = id;
		this.debilidad = debilidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDebilidad() {
		return debilidad;
	}

	public void setDebilidad(String debilidad) {
		this.debilidad = debilidad;
	}
}