/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashingimpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Ashish
 */
public class HashingImpl<T> {

    /**
     * @param args the command line arguments
     */
    static class HashFunction{
        int hash(String s){
            System.out.println("HashCode of "+s+" = "+s.hashCode());
            return s.hashCode();
        }
    }
    
    private final HashFunction hashFunction;  
    private final int numberOfReplicas;  
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();  
    //private final Set<T> set = new HashSet<T>();
 
    public HashingImpl(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {  
        this.hashFunction = hashFunction;  
        this.numberOfReplicas = numberOfReplicas;  
 
        for (T node : nodes) {  
            add(node);  
        }  
    }  
 
    public void add(T node) { 
        //set.add(node);
        for (int i = 0; i <numberOfReplicas; i++) {  
            circle.put(hashFunction.hash(node.toString() + i), node);  
        }  
    }  
 
    public void remove(T node) { 
        //set.remove(node);
        for (int i = 0; i <numberOfReplicas; i++) {  
            circle.remove(hashFunction.hash(node.toString() + i));  
        }  
    }  
 
    public T get(Object key) {  
        if (circle.isEmpty()) {  
            return null;  
        } 
        
        int hash = hashFunction.hash((String) key);  
        if (!circle.containsKey(hash)) {  
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);  
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey(); 
//            return null;
        }  
        return circle.get(hash);  
 }  
    public static void main(String[] args) {
        // TODO code application logic here
        List<String> nodeStr = new LinkedList<String>();
        nodeStr.add("ashish");
        nodeStr.add("gaurav");
        nodeStr.add("sarthak");
        nodeStr.add("vibhor");
        nodeStr.add("jindal");
        HashingImpl hs = new HashingImpl(new HashFunction(), 3, nodeStr);
//        System.out.println(hs.get("as"));
        
//        System.out.println();
//        System.out.println(hs.circle.size());
//        hs.remove("as");//incorrect. remove() is for removing cache node only.
        //by HashinImpl we just get the cache node required where our data will be stored. Thats it.
        //we would need to create a separate mapping to store this string value.
        //this get() function is only to get the name of cahce node where our data should be sotored.
        //add() and remove() methods in hashingimpl are only for dding cahce nodes.
        //hence, the above line do not have any effect
        HashMap<String, Object> consisMap = new HashMap<String, Object>();
        consisMap.put("as", hs.get("as"));
        consisMap.put("abracadabra", hs.get("abracadabra"));
        consisMap.put("Ashish", hs.get("Ashish"));
        consisMap.put("Sarthak", hs.get("Sarthak"));
        consisMap.put("Chaynika", hs.get("Chaynika"));
        consisMap.put("California", hs.get("California"));
        System.out.println(consisMap.get("as"));
        System.out.println(consisMap.get("abracadabra"));
        System.out.println(consisMap.get("Ashish"));
        System.out.println(consisMap.get("Sarthak"));
        System.out.println(consisMap.get("Chaynika"));
        System.out.println(consisMap.get("California"));
        hs.remove("sarthak");
        //now we need to find the next key in the circle map present next to "sarthak"
        //and allocate all the strings mapped to "sarthak" to the next node we get
        //below code is not the implementation of this anyways.
        System.out.println(hs.circle.size());
        consisMap.put("as", hs.get("as"));
        consisMap.put("abracadabra", hs.get("abracadabra"));
        consisMap.put("Ashish", hs.get("Ashish"));
        consisMap.put("Sarthak", hs.get("Sarthak"));
        consisMap.put("Chaynika", hs.get("Chaynika"));
        consisMap.put("California", hs.get("California"));
        System.out.println(consisMap.get("as"));
        System.out.println(consisMap.get("abracadabra"));
        System.out.println(consisMap.get("Ashish"));
        System.out.println(consisMap.get("Sarthak"));
        System.out.println(consisMap.get("Chaynika"));
        System.out.println(consisMap.get("California"));
//        System.out.println(consisMap.get("abracadabra"));
    }
    
}
