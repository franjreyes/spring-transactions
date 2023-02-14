package dual.transacciones.superheroes.excepciones;

@SuppressWarnings("serial")
public class ImagenException extends RuntimeException{

	public ImagenException() {
	}

	public ImagenException(String mensajeError) {
		super(mensajeError);
	}
}
