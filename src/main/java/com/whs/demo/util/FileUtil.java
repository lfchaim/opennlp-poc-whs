package com.whs.demo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readFile( String dir, String fileName ){
		List<String> ret = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(new File(dir,fileName));
			br = new BufferedReader(fr);
			String line = null;
			while( (line=br.readLine()) != null ) {
				ret.add(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {br.close();}catch(Exception e) {}
			try {fr.close();}catch(Exception e) {}
		}
		return ret;
	}
	
	public static void write( String dir, String fileName, boolean append, String content ) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File(dir,fileName),append);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {bw.close();}catch(Exception e) {}
			try {fw.close();}catch(Exception e) {}			
		}
	}
	
	public static boolean delete(String dir, String fileName) {
		return new File(dir,fileName).delete();
	}
	
	public static boolean createFile(String dir, String fileName) {
		File file = new File(dir,fileName);
		boolean ret = false;
		try{ret = file.createNewFile();}catch(Exception e) {}
		return ret;
	}
}
