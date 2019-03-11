package tatictest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tatictest.service.ArmazenadorService;
import tatictest.service.BuscadorService;
import tatictest.service.CompactarService;

public class Main {

	public static final String PATH = "arquivos/";
	public static final String SEPARATOR = ";";
	public static final String QUEBRA = "\r\n";
	public static final String ENDS = ".zip";

	private static final String ARMAZENADOR = "armazenador";
	private static final String BUSCADOR = "buscador";
	private static final String SAIR = "sair";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String acao = "";
		Boolean sair = false;
		String[] aux;

		while (!sair) {
			File file = new File(PATH);

			Main.mostrarMenu();

			acao = scanner.nextLine();
			aux = acao.split(" ");

			if (aux[0].equals(SAIR)) {
				sair = true;
				scanner.close();
				System.out.println("Saindo ...");
			} else if (aux[0].equals(BUSCADOR)) {
				Main.buscador(file, aux);
			} else if (aux[0].equals(ARMAZENADOR)) {
				armazenador(file, aux);
			} else {
				System.out.println("Comando inválido");
			}
		}
	}

	private static void buscador(File file, String[] aux) {
		if (file.exists() && file.list().length != 0) {
			if (aux.length >= 3) {
				List<String> ids = new ArrayList<String>();

				for (int i = 3; i < aux.length; i++) {
					ids.add(aux[i]);
				}

				BuscadorService buscador = new BuscadorService();
				buscador.buscar(aux[1], aux[2], ids);
			} else {
				System.out.println("A busca deve conter no minimo dataInicial e dataFinal");
			}
		} else {
			System.out.println("Antes de começar use o comando de armazenamento");
		}
	}

	private static void armazenador(File file, String[] aux) {
		if (file.exists()) {
			File[] sun = file.listFiles();

			for (File toDelete : sun) {
				toDelete.delete();
			}
		} else {
			file.mkdir();
		}

		if (aux.length == 2) {
			System.out.println("Armazenando dados ...");

			if (ArmazenadorService.armazenaDados(aux[1])) {
				System.out.println("Compactando dados ...");
				CompactarService.compactar();
			}
		} else {
			System.out.println("O armazenamento deve receber o arquivo como parametro");
		}
	}
	
	private static void mostrarMenu() {
		System.out.println("\n\n*****************************************************");
		System.out.println("* Comandos:                                         *");
		System.out.println("* armazenador 'nomeArquivo'                         *");
		System.out.println("* buscador 'dataInicio' 'dataFim' 'ids..(Opcional)' *");
		System.out.println("* sair                                              *");
		System.out.println("*****************************************************\n\n");
	}
}
