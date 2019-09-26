package zavrsniProjekat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Knjiga {
	HashMap<String, Integer> knjiga = new HashMap<>();
	
	public Knjiga(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String[] split;
		String in = br.readLine();
		while (in!=null) {
			split = in.replaceAll("[^A-Za-z]", " ").split(" +");
			for (String el: split) {
				el = el.toLowerCase();
				if (el!=null&&el.length()>1) el = capitalize(el);
				if (!el.equals("")&&knjiga.containsKey(el)) {
					knjiga.put(capitalize(el), (knjiga.get(el)+1));
				} else {
					if (!el.equals("")) knjiga.put(el, 1);
				}
			}
			in = br.readLine();
		}
	}
	
	public ArrayList<String> nepostojeceReci(Set<String> recnik) {
		ArrayList<String> nepostojece = new ArrayList<>();
		for (String el: knjiga.keySet()) {
			if (el!=null&&el.length()>1&&recnik.contains(capitalize(el))) {
				continue;
			}	else if (el.length()==1){
				continue;
			} else {
				if (el!=null&&el.length()>1)nepostojece.add(capitalize(el));
				else if (el!=null&&!el.equals("")) nepostojece.add(el);
			}
		}
		return nepostojece;
	}
	
	private String capitalize (String x) {
		return x.substring(0,1).toUpperCase()+x.substring(1);
	}
	
	public LinkedHashMap<String, Integer> sortByValues(HashMap<String,Integer> passedMap) {
	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Integer> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);
	    Collections.reverse(mapValues);
	    Collections.reverse(mapKeys);
	    LinkedHashMap<String, Integer> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = passedMap.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	public void najcesce() {
		int ct=0;
		HashMap<String, Integer> sorted = sortByValues(knjiga);
		for (String el: sorted.keySet()) {
			
			System.out.println(el+" " + sorted.get(el) );
			if (ct++>18) return;
		}
}
	public void sveReci() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("fajl.txt"));
		String sum = "";
		for (String el: new TreeMap<String, Integer>(knjiga).keySet()) {
			sum += el+"\n";
		}
		bw.write(sum);
		bw.close();
	}
}