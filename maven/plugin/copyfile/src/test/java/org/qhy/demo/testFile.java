package org.qhy.demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class testFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileUtils.deleteDirectory(new File("E:\\filetest\\a"));
		FileUtils.copyDirectory(new File("D:\\devtools\\Java\\jre6"), new File("E:\\filetest\\a"));
	}

}
