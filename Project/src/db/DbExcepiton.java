package db;

public class DbExcepiton extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DbExcepiton (String mensagem) {
		super(mensagem);
	}
}
