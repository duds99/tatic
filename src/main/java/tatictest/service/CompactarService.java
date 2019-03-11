package tatictest.service;

import static tatictest.Main.ENDS;
import static tatictest.Main.PATH;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompactarService {
	
	private CompactarService() {
	}

	private static final int BUFFER = 200 * 41;

	public static void compactar() {
		File file = new File(PATH);
		String[] arquivos = file.list();

		for (int i = 0; i < arquivos.length; i++) {
			if(arquivos[i].endsWith(ENDS)) {
				continue;
			}
			
			try {
				FileInputStream fis = new FileInputStream(PATH + arquivos[i]);
				FileOutputStream fos = new FileOutputStream(PATH + arquivos[i] + ENDS);
				GZIPOutputStream gzipOS = new GZIPOutputStream(fos);

				byte[] buffer = new byte[BUFFER];
				int len;

				while ((len = fis.read(buffer)) != -1) {
					gzipOS.write(buffer, 0, len);
				}

				file = new File(PATH + arquivos[i]);
				file.delete();

				gzipOS.close();
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String descompactar(String arqNome, List<String> ids) {
		String resp = "";
		
		if(!arqNome.endsWith(ENDS)) {
			return null;
		}
		
		try {
			FileInputStream fis = new FileInputStream(PATH + arqNome);
			GZIPInputStream gis = new GZIPInputStream(fis);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[BUFFER];
			int len;
			
			while ((len = gis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				resp += baos.toString() + "\n";
			}
			
			baos.close();
			gis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
