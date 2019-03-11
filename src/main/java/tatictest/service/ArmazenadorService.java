package tatictest.service;

import static tatictest.Main.PATH;
import static tatictest.Main.SEPARATOR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArmazenadorService {
	
	public static Boolean armazenaDados(String pathFile) {
		Boolean resp = false;

		if (pathFile != null && !pathFile.isEmpty()) {
			try {
				FileReader arq = new FileReader(pathFile);
				BufferedReader lerArq = new BufferedReader(arq);

				String linha = lerArq.readLine();

				while (linha != null) {
					String[] aux = linha.split(SEPARATOR);
					escreverArquivo(aux);

					linha = lerArq.readLine();
				}

				lerArq.close();
				arq.close();

				resp = true;
			} catch (IOException e) {
				System.out.println("Arquivo inválido!");
				return false;
			}
		}

		return resp;
	}

	private static void escreverArquivo(String[] linha) {
		try {
			File file = new File(PATH + linha[0].substring(0, 10));

			if (file.exists()) {
				FileWriter arquivo = new FileWriter(file, true);
				BufferedWriter conexao = new BufferedWriter(arquivo);
				
				conexao.newLine();
				conexao.write(linha[0] + SEPARATOR + linha[1] + SEPARATOR + linha[2]);
				
				conexao.close();
				arquivo.close();
			} else {
				FileWriter arquivo = new FileWriter(file);
				arquivo.write(linha[0] + SEPARATOR + linha[1] + SEPARATOR + linha[2]);
				
				arquivo.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
