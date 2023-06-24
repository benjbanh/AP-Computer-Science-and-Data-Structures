import java.util.Set;
import java.util.HashSet;


/**
 * @author Benjamin Banh 
 * 
 * Reflection:
 * This does not test very well, there are constant collisions as my hash method 
 * divides the hashCode() method by modulo(%). The program sometimes crashes due
 * to % returning null. Since the possible keys are constrained to the size of
 * the hashTable, there is a risk of duplicate hashcodes and collisions. All in all,
 * this hashTable is not very good.
*/


public class YoHashMapTester_Banh {
    public static void main(String[] args) {
        System.out.println("Starting!");
        HashMap map = new HashMap();
        
        System.out.println("Putting:");
        map.put("A",0);
        map.put("B",1);
        map.put("C",2);
        map.put("D",3);
        map.put("E",4);
        map.put("F",5);
        map.put("G",6);
        map.printAll();
        
        System.out.println("Testing Methods:");
        System.out.println("    Get D: " + map.get("D"));
        System.out.println("    Contains B: " + map.containsKey("B"));
        System.out.println("    Contains R: " + map.containsKey("R"));
        System.out.println("    Remove E: ");
                                    map.remove("A");
        System.out.println("    Size: " + map.size());
        
        System.out.println("Print Set:");
        Set<Object> setMap = map.keySet();
        for(Object str: setMap)
            System.out.print("  " + str);
        
    }
}
/**
 * A class that produces a HashTable using the Hash_Interface interface.
 * It mimics java.util.hashtable with methods such as get, put, remove etc.
 * This HashMap uses objects instead of generics. 
 */
class HashMap implements Hash_Interface{
    
    static private class HashNode {
        private Object key;
        private Object value;
     
        public HashNode(Object key, Object value)
        {
            this.key = key;
            this.value = value;
        }
    }
    
    private HashNode[] bucket;
    private int size;
    private int load;

    /**
     * Instantiates bucket to the default size of 10
     */
    public HashMap(){
        this.bucket = new HashNode[10];
        this.load = 0;
        this.size = 10;
    }

    /**
     * instantiates bucket to the size given
     * @param size The size of the newly created arraylist
     */
    public HashMap(int size){
        this.bucket = new HashNode[size];
        this.load = 0;
        this.size = size;

    }

    /**
     * sets all elements of Arraylist bucket to null
     */
    public void clear(){
        this.bucket = new HashNode[size];
    }

    /**
     * Returns true when the Table contains the key and false if it doesn't
     * @return whether or not the Table has the key
     * @param key the key we are searching for in the Table
     */
    public boolean containsKey(Object key){
        for(HashNode node: bucket){
            if( node == null)
                continue;
            else if( node.key == key )
                return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null 
     * if this map contains no mapping for the key.
     * @param key The key of the Node that we are searching the value of
     * @return the value associated with the parameter key
     */
    public Object get(Object key) {
        int hash =  hash(key);
        for(HashNode node: bucket){
            if(hash(node.key) == hash)
                return node.value;
        }
        return null;
    }

    /**
     * Returns a Set view of the keys contained in this map. 
     * Translates the table to a set and then returns it
     * @return a set that has all elements of bucket
     */
    public Set<Object> keySet() {
        Set<Object> hashSet = new HashSet<Object>();
        for(HashNode node: bucket){
            if(node == null){
                hashSet.add(null);
                continue;
            }   
            hashSet.add(node.key);
        }
        return hashSet;
    }

    /**
     * Associates the specified value with the specified key in this map. Basically
     * the add method of an arraylist. But if the map previously contained a 
     * mapping for the key, the old value is replaced.
     * @param key   the location of index
     * @param value value of key
     */
    public void put(Object key, Object value) {
        boolean found = false;
        
        if(bucket[0] == null){
            HashNode newNode = new HashNode(key,value);
            bucket[load] = newNode;
            load++;
            return;
        }

        for(int i = 0; i < this.size-1; i++){
            if(bucket[i] == null) 
                continue;
            if (bucket[i].key == key){  
                bucket[i].value = value; 
                found = true;
                break;
            }
        }

        if(!found) {
            if ( size * 0.75 > load ) {
                resize();
            }

            HashNode newNode = new HashNode(key,value);
            bucket[load] = newNode;
            load++;
        }
    }

    /**
     * Removes the element from the Array bucket 
     * @param the value of the key to be removed
     */
    public void remove(Object key) {  
        int hash = hash(key);  
        if (bucket[hash] == null) {
           return; 
        }
        if (bucket[hash].key == key) {
           delete(bucket[hash]);
           load--; 
           return;
        } 

        for(int i = 0; i < this.size; i++){
            if(bucket[i] != null && bucket[i].key == key){
                delete(i);
            }
        }
    }
    /**
     * Used in the remove method to delete elements and shift the other
     * elements behind the removed one forward. Deletes using the index 
     * of the array.
     * @param index the index to be removed
     */
    public void delete(int index){
        boolean found = false;
        for(int i = 0; i < size; i++){
            if(found){
                bucket[i-1] = bucket[i];
            }
            else if(i == index){
                found = true;
                bucket[i] = null;
            }
        }
    }

    /**
     * Used in the remove method to delete elements and shift the other
     * elements behind the removed one forward. Deletes if the parameter is 
     * the same as in the array
     * @param obj the object that is to be removed 
     */
    public void delete(Object obj){
        boolean found = false;
        for(int i = 0; i < size-1; i++){
            if(found){
                bucket[i-1] = bucket[i];
            }
            else if(bucket[i] == obj){
                found = true;
                bucket[i] = null;
            }
        }
    }

    /**
     * returns the total capacity of the arrayList
     * @return the size of buckets
     */
    public int size( ){
        return this.size;
    }

    /**
     * Hashing function using hashCode() method and %
     * @param key the key to be inputted into the hashCode()
     * @return an integer ranging from null to size
     */
    private int hash(Object key){
        return (Math.abs(key.hashCode())) % bucket.length;
    }

    /**
     * Resizes the array to grow only larger
     */
    private void resize(){
        if(size * 0.75 <= load)
            this.size *= 2;

        Object[] newArr = new Object[size];
        for(int i = 0; i < size; i++){
            newArr[i] = bucket[i];
        }
    }

    /**
     * used to print all elements in bucket
     */
    public void printAll(){
        for(HashNode node: bucket){
            if(node != null)
                System.out.println("   Key: " + node.key + 
                                    " Value: " + node.value);
        }
    }
} 
    /**
     * interface for looking nice
     */
    interface Hash_Interface{
        void clear();
        boolean containsKey(Object key);
        Object get(Object key);
        Set<Object> keySet();            
        void put( Object key, Object value );
        void remove( Object key );
        int size( );
    }