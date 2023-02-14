package dual.transacciones.superheroes.excepciones;

@SuppressWarnings("serial")
public class SuperheroeException extends Exception{

	public SuperheroeException() {
	}

	public SuperheroeException(String mensajeError) {
		super(mensajeError);
	}
}
