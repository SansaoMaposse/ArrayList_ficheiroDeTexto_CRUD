
public class Estudante {

	private int codigo;
	private String nome;
	private double nota1;
	private double nota2;

	public Estudante(int codigo, String nome, double nota1, double nota2) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nota1 = nota1;
		this.nota2 = nota2;
	}

	public String toString() {
		double media = calcularMedia();
		return codigo + "-" + nome + "-" + nota1 + 
				"-" + nota2 + "-" + media + "-" + situacao(media);
	}

	public double calcularMedia() {
		return (nota1 + nota2) / 2;
	}

	public String situacao(double media) {
		String situacao = "";
		if (media > 13.5) {
			situacao = "Dispensado";
		} else if (media > 9.5) {
			situacao = "Admitido";
		} else {
			situacao = "Excluido";
		}
		return situacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

}
