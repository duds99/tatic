package tatictest.service;

import static org.junit.Assert.assertTrue;
import static tatictest.Main.PATH;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tatictest.Main;

@RunWith(JUnit4.class)
public class BuscadorServiceTest {

	private BuscadorService buscadorService;

	private File file;

	@Before
	public void setUp() {
		file = new File(PATH);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		file = new File(PATH + "1992020618");
		buscadorService = new BuscadorService();
	}

	@Test
	public void buscar_semDatasESemLista_returnFalse() {
		Boolean resp = buscadorService.buscar(null, null, null);
		assertTrue(!resp);
	}

	@Test
	public void buscar_semDatas_returnFalse() {
		Boolean resp = buscadorService.buscar(null, null, Arrays.asList("BFD99205"));
		assertTrue(!resp);
	}

	@Test
	public void buscar_comDataSemLista_returnTrue() throws IOException {
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("19920206185709746;0A23C8B5;553140270338");
        out.close();
        
        CompactarService.compactar();
        
		Boolean resp = buscadorService.buscar("19910206185709743", "19930206185709744", null);
		assertTrue(resp);
	}
	
	@Test
	public void buscar_comDataComLista_returnTrue() throws IOException {
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("19920206185709746;0A23C8B5;553140270338");
        out.close();
        
        CompactarService.compactar();
        
        List<String> lista = new ArrayList<String>();
        lista.add("0A23C8B5");
        
		Boolean resp = buscadorService.buscar("19910206185709743", "19930206185709744", lista);
		assertTrue(resp);
	}
	
	@Test
	public void buscar_comDataComListaValorErrado_returnTrue() throws IOException {
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("19920206185709746;0A23C8B5;553140270338");
        out.close();
        
        CompactarService.compactar();
        
        List<String> lista = new ArrayList<String>();
        lista.add("0A23CFD5");
        
		Boolean resp = buscadorService.buscar("19910206185709743", "19930206185709744", lista);
		assertTrue(resp);
	}
	
	@After
	public void exit() {
		file.delete();
		file = new File(PATH + "1992020618" + Main.ENDS);
		file.delete();
	}
}
