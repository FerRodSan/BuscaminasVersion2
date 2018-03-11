package Modelo;

public class Casilla {
	private boolean mina = false;
	private boolean velada = true;
	private boolean marcada = false;
	private int minasAlrededor = 0;

	public boolean isMina() {
		return mina;
	}

	void setMina(boolean mina) {
		this.mina = mina;
	}

	public boolean isVelada() {
		return velada;
	}

	void setVelada(boolean velada) {
		this.velada = velada;
	}

	public boolean isMarcada() {
		return marcada;
	}

	protected void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}

	public int getMinasAlrededor() {
		return minasAlrededor;
	}

	void setMinasAlrededor(int minasAlrededor) {
		this.minasAlrededor = minasAlrededor;
	}

}
