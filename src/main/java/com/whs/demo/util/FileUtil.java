package com.whs.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
}
