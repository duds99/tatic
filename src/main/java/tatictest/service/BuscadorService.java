package tatictest.service;

import static tatictest.Main.PATH;
import static tatictest.Main.QUEBRA;
import static tatictest.Main.SEPARATOR;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorService {

	public Boolean buscar(String dataInicial, String dataFinal, List<String> lista) {
		Boolean bool = false;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddhh");

			if ((dataInicial != null && dataInicial.length() == 17)
					|| (dataFinal != null && dataFinal.length() == 17)) {
				Date dataIni = formato.parse(dataInicial.substring(0, 10));
				Date dataFim = formato.parse(dataFinal.substring(0, 10));

				File file = new File(PATH);
				String[] arquivos = file.list();

				for (int i = 0; i < arquivos.length; i++) {
					Date dataArquivo = formato.parse(arquivos[i].substring(0, 10));

					if (dataIni.before(dataArquivo) && dataFim.after(dataArquivo)) {
						String resp = CompactarService.descompactar(arquivos[i], lista);

						if (lista == null || lista.isEmpty()) {
							bool = true;
							System.out.println(resp);
						} else {
							List<String> respList = Arrays.asList(resp.split(QUEBRA));

							respList = respList.stream().filter(evento -> {
								String[] aux = evento.split(SEPARATOR);
								if (lista.contains(aux[1])) {
									lista.remove(aux[1]);
									return true;
								}
								return false;
							}).collect(Collectors.toList());

							respList.forEach(evento -> System.out.println(evento.split("\n")[0]));

							bool = true;

							if (lista.isEmpty()) {
								break;
							}
						}
					}
				}

				System.out.println("\nFim da busca!");
			} else {
				System.out.println("Parametros inválidos");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return bool;
	}
}
