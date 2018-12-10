/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pranav
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pranav
 */

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

public class ExtractFiles {

	public static void main(String[] args) throws IOException {
            HashMap<String, Integer> fileNames = new HashMap<String,Integer>();
            String username = System.getProperty("user.name");
            System.out.println("username = " + username);
                String location = "C:\\Users\\" + username + "\\Downloads\\"; 
		File f = new File(location); // current directory

		FilenameFilter textFilter = new FilenameFilter() {
                        @Override
			public boolean accept(File dir, String name) {
				
				if (name.startsWith("ITR")) {
					return true;
				} else {
					return false;
				}
			}
		};

		File[] files = f.listFiles(textFilter);
                System.out.println(files.length);
		for (File file : files)
                {
                    System.out.println(file.getCanonicalPath());
                    if(fileNames.containsKey(file.getName()))
                    {
                        int count = fileNames.get(file.getName())+1;
                    fileNames.put(file.getName(), count);
                    }
                    else
                        fileNames.put(file.getName(), 1);
                }
                
                Set<String> name = fileNames.keySet();
                System.out.println("Names of files: " +name);
                System.out.println(fileNames);
                Iterator itr = name.iterator();
                Set<String> dirNames = new HashSet<String>();
                while(itr.hasNext())
                {
                    String s = (String)itr.next();
                    String s1= s.substring(0, 14);
                    if(dirNames.contains(s1))
                       continue;
                    else
                        dirNames.add(s1);
                    
                }
                System.out.println("Names of Directories to be formed: " + dirNames);
                String outputDir = "D:\\IncomeTax"; 
                File rootDir  = new File(outputDir);
                if(rootDir.exists()==false)
                   rootDir.mkdir();
                
                Iterator createClientDirs = dirNames.iterator();
                while(createClientDirs.hasNext())
                {
                    String temp = createClientDirs.next().toString();
                    String path = "D:\\IncomeTax\\" + temp;
                    
                    System.out.println("Currently: " + temp);
                    File clients  = new File(path);
                    if(!clients.exists())
                        clients.mkdir();
                    System.out.println(temp);
                    FilenameFilter filesFilter = new FilenameFilter() {
                        @Override
			public boolean accept(File dir, String name) {
				
				if (name.startsWith(temp)) {
					return true;
				} else {
					return false;
				}
			}
		};

		File[] clientFiles = f.listFiles(filesFilter);
                System.out.println(clientFiles.length);
		for (File file : clientFiles)
                {
                    String sourcePath = file.getCanonicalPath();
                    String fileName = file.getName();
                    String destPath = "D:\\IncomeTax\\" + temp +"\\"+fileName; 
                    System.out.println("Cuurent Location of file " + fileName +" is: " + sourcePath + " and destination is: "+ destPath);
                    Path loc = Files.move(Paths.get(sourcePath),Paths.get(destPath));
                }

                }
                
                 

             
             
             
             
              
             
             
             
             
             
	}

}


