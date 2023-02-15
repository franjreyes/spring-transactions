package dual.transacciones.superheroes.dao.modelo;

public class SuperheroeDebilidad {

    private Integer superheroeId;

    private Integer debilidadId;

	public SuperheroeDebilidad(Integer superheroeId, Integer debilidadId) {
		super();
		this.superheroeId = superheroeId;
		this.debilidadId = debilidadId;
	}

	public Integer getSuperheroeId() {
		return superheroeId;
	}

	public void setSuperheroeId(Integer superheroeId) {
		this.superheroeId = superheroeId;
	}

	public Integer getDebilidadId() {
		return debilidadId;
	}

	public void setDebilidadId(Integer debilidadId) {
		this.debilidadId = debilidadId;
	}
}