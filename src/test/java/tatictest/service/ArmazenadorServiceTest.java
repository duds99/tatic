package tatictest.service;

import static org.junit.Assert.assertTrue;
import static tatictest.Main.PATH;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArmazenadorServiceTest {
	
	private File file;
	
	private ArmazenadorService armazenadorService;
	
	@Before
	public void setUp() {
		file = new File(PATH);
		if(!file.exists()) {
			file.mkdir();
		}
		
		file = new File("9999020618");
		armazenadorService = new ArmazenadorService();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void armazenar_semPath_returnFalse() {
		Boolean resp = armazenadorService.armazenaDados(null);
		assertTrue(!resp);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void armazenar_semPath_returnTrue() throws IOException {
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("99990206181545876;0A23C8B5;553140270338");
        out.close();
        
		Boolean resp = armazenadorService.armazenaDados("9999020618");
		assertTrue(resp);
	}
	
	@After
	public void exit() {
		file.delete();
		file = new File(PATH + "9999020618");
		file.delete();
	}
}
