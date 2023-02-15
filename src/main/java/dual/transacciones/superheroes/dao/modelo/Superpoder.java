package dual.transacciones.superheroes.dao.modelo;

public class Superpoder {

    private Integer id;

    private String superpoder;

	public Superpoder(Integer id, String superpoder) {
		super();
		this.id = id;
		this.superpoder = superpoder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuperpoder() {
		return superpoder;
	}

	public void setSuperpoder(String superpoder) {
		this.superpoder = superpoder;
	}
}