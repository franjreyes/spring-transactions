package dual.transacciones.superheroes.dao.modelo;

public class SuperheroeSuperpoder {

    private Integer superheroeId;

    private Integer superpoderId;

	public SuperheroeSuperpoder(Integer superheroeId, Integer superpoderId) {
		super();
		this.superheroeId = superheroeId;
		this.superpoderId = superpoderId;
	}

	public Integer getSuperheroeId() {
		return superheroeId;
	}

	public void setSuperheroeId(Integer superheroeId) {
		this.superheroeId = superheroeId;
	}

	public Integer getSuperpoderId() {
		return superpoderId;
	}

	public void setSuperpoderId(Integer superpoderId) {
		this.superpoderId = superpoderId;
	}
}