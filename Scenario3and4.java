package csv;

import java.io.*;
import java.util.*;

public class Scenario3and4{
	static Map<String,Integer> map = new HashMap<>();
	static Map<String,Integer> map1= new HashMap<>();
	static Map<String,Integer> map2 = new HashMap<>();
	static List<Integer> ids2016 = new ArrayList<>();
	static List<Integer> ids2015 = new ArrayList<>();
	
	static void ExtraRunsConcededPerTeam2016() {
		for(Map.Entry<String,Integer> entry:map.entrySet())
			System.out.println(entry.getKey()+" : "+entry.getValue());
	}
	
	static void HighestEconomy2015() {	
		for(Map.Entry<String,Integer> entry:map2.entrySet())
		{
			String bowler = entry.getKey();
			for(Map.Entry<String,Integer> entry1:map1.entrySet()) {
				if(entry1.getKey().equals(bowler))
				{
					map2.put(bowler, map2.get(bowler)/entry1.getValue());
					break;
				}
			}
		}
		for(Map.Entry<String,Integer> entry:map2.entrySet())
			System.out.println(entry.getKey()+" : "+entry.getValue());
	}
	public static void main(String[] args) {
		String matches = "/home/jyothi/Downloads/archive (1)/matches.csv";
		String deliveries = "/home/jyothi/Downloads/archive/deliveries.csv";
		
		BufferedReader reader = null;
		BufferedReader reader1 = null;
		String line = "";
		int flag=0;
		
		try {
			reader = new BufferedReader(new FileReader(matches));
			while((line = reader.readLine())!=null){
				if(flag==0) {
					flag=1;
					continue;
				}
				else {
					String []row = line.split(",");
					
						int yr = Integer.valueOf(row[1]);
						String team =  row[10];
						if(yr==2016)
							ids2016.add(Integer.valueOf(row[0]));
						if(yr==2015)
							ids2015.add(Integer.valueOf(row[0]));
					
				}
		}
		
			
			
			flag = 0;
			reader1 = new BufferedReader(new FileReader(deliveries));
			while((line = reader1.readLine())!=null){
				if(flag==0) {
					flag=1;
					continue;
				}
				else {
					String []row = line.split(",");
				
						int matchId = Integer.valueOf(row[0]);
						String team = row[2];
						int idx = map.get(team)!=null?map.get(team):0;
						int extra = Integer.valueOf(row[16]);
						if(ids2016.contains(matchId)) {
							map.put(team, idx+extra);
						}
						if(ids2015.contains(matchId)) {
							String bowler = row[8];
							int runs = Integer.valueOf(row[17]);
							int idx1 = map1.get(bowler)!=null?map1.get(bowler):0;
							map1.put(bowler, idx1+1);
							int idx2 = map2.get(bowler)!=null?map2.get(bowler):0;
							map2.put(bowler, idx2+runs);
						
					}
				}
			}
			System.out.println( "For the year 2016 get the extra runs conceded per team");
			ExtraRunsConcededPerTeam2016();
			System.out.println();
		System.out.println("For the year 2015 get the top economical bowlers");
		
			HighestEconomy2015();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
