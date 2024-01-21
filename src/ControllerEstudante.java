import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerEstudante {

	public static void adicionar(int codigo, String nome, double nota1, double nota2) throws IOException {
		FileWriter ficheiro = new FileWriter("estudantes.txt", true);
		BufferedWriter escrever = new BufferedWriter(ficheiro);

		Estudante estudante = new Estudante(codigo, nome, nota1, nota2);
		escrever.write(estudante.toString());
		escrever.newLine();
		escrever.close();
		ficheiro.close();
	}

	public static ArrayList<Estudante> listaDeEstudante() throws IOException {
		ArrayList<Estudante> lista = new ArrayList<Estudante>();
		FileReader ficheiro = new FileReader("estudantes.txt");
		BufferedReader ler = new BufferedReader(ficheiro);

		String linha = ler.readLine();
		String dadosDoEstudante[];

		while (linha != null && !linha.equals("")) {
			dadosDoEstudante = linha.split("-");

			int codigo = Integer.parseInt(dadosDoEstudante[0]);
			String nome = dadosDoEstudante[1];
			double nota1 = Double.parseDouble(dadosDoEstudante[2]);
			double nota2 = Double.parseDouble(dadosDoEstudante[3]);

			lista.add(new Estudante(codigo, nome, nota1, nota2));
			linha = ler.readLine();
		}
		ler.close();
		ficheiro.close();
		return lista;
	}

	public static void actualizar(int codigo, String nome, double nota1, double nota2) throws IOException {
		ArrayList<Estudante> lista = listaDeEstudante();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo) {
				Estudante estudante = lista.get(i);
				estudante.setNome(nome);
				estudante.setNota1(nota1);
				estudante.setNota2(nota2);
			}
		}
		FileWriter ficheiro = new FileWriter("estudantes.txt");
		BufferedWriter escrever = new BufferedWriter(ficheiro);

		for(int x = 0; x < lista.size(); x++) {
			escrever.write(lista.get(x).toString());
			escrever.newLine();
		}
		escrever.close();
		ficheiro.close();
	}

	public static void remover(int codigo) throws IOException {
		ArrayList<Estudante> lista = listaDeEstudante();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo) {
				Estudante estudante = lista.get(i);
				//lista.remove(i);
				lista.remove(estudante);
			}
		}
		FileWriter ficheiro = new FileWriter("estudantes.txt");
		BufferedWriter escrever = new BufferedWriter(ficheiro);

		for(int x = 0; x < lista.size(); x++) {
			escrever.write(lista.get(x).toString());
			escrever.newLine();
		}
		escrever.close();
		ficheiro.close();
	}
}
