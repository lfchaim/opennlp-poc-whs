package com.whs.demo.train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.whs.demo.util.FileUtil;

public class NomeTrain {

	public static void main(String[] args) {
		//System.setSecurityManager(new SecurityManager());
		String dir = "./";
		List<String> listName = new ArrayList<String>();
		List<String> list = FileUtil.readFile(dir, "nomes.txt");
		System.out.println(list.size());
		List<String> prep = new ArrayList<String>(Arrays.asList(new String[]{"de", "do", "dos", "das", "da"}));
		String ch = ";";
		int ini = 0;
		int endLen = 3;
		for( int i = 2; i < list.size(); i++ ) {
			String[] line = list.get(i).split(ch);
			StringBuffer sb = new StringBuffer();
			String name = line[0].toLowerCase(); // NOME DEVE SER A PRIMEIRA OCORRENCIA
			String[] partName = name.split(" ");
			for( int j = 0; j < partName.length; j++ ) {
				try {
					if( !prep.contains(partName[j]) ) {
						sb.append(partName[j].substring(0,1).toUpperCase()).append(partName[j].substring(1)).append(" ");
					}else {
						sb.append(partName[j]).append(" ");
					}
				}catch(Exception e) {
					System.err.println("ERRO Nome: ["+line[0]);
				}
			}
			if( !listName.contains(sb.toString().trim()) ) {
				listName.add(sb.toString().trim());
				System.out.println(sb.toString());
			}
		}
		FileUtil.delete(dir, "nomes-train.txt");
		FileUtil.createFile(dir, "nomes-train.txt");
		for( int i = 0; i < listName.size(); i++ ) {
			String line = "<START:person> "+listName.get(i)+" <END>\n";
			FileUtil.write(dir, "nomes-train.txt", true, line);
		}
		System.out.println("FIM");
		System.exit(0);
	}

}
