package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class scenario2 {
	static HashMap<String,Integer> map = new HashMap<>();
	public static void main(String[] args) {
		String file="/home/jyothi/Downloads/archive (1)/matches.csv";
		BufferedReader reader=null;
		String line= "";
		int flag=0;
		try {
		reader =new BufferedReader(new FileReader(file));
		while((line=reader.readLine())!=null)
		{		
			if(flag==0) {
				flag=1;
			continue;
			}	
			       String[] row=line.split(",");
			            if(map.containsKey(row[10]))
			            {
					    map.put(row[10],map.get(row[10])+1);
					
			             }
				         else {
				             map.put(row[10],1);
				             }
			}
			
		       System.out.println(map);
		}
		catch(Exception e){
			e.printStackTrace();
		               }
		
		             finally {
			                 try {
				                 reader.close();
		                     	} catch (IOException e) {
			                         e.printStackTrace();
			                          }
	                        	}

		}
}