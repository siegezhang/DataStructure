package basic.hashmapcustom;

/**
 * Created by siege on 2017/8/10.
 */
public class HashMapCustom<K, V> {
    private Entry<K, V>[] table;
    private int capacity=4;

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public HashMapCustom() {
        table=new Entry[capacity];
    }

    public void put(K key,V value){
        if (key==null)return;
        int index=hashCode(key);
        Entry<K,V> next=null;
        Entry<K,V> tmp=table[index];
        Entry<K,V> previous=tmp;
        if (tmp==null){
            table[index]=new Entry<K,V>(key,value,next);
        }else {
            while (tmp!=null){
                if (tmp.key.equals(key)){
                    tmp.value=value;
                    return;
                }
                previous=tmp;
                tmp=tmp.next;
            }
            previous.next=new Entry<K,V>(key,value,next);
        }
    }

    public V get(K key){
        if (key==null)return null;
        int hash=hashCode(key);
        Entry<K,V>  tmp=table[hash];
        while (tmp!=null){
            if (key.equals(tmp.key))
                return tmp.value;
            else
                tmp=tmp.next;
        }
        return null;
    }

    public boolean remove(K deleteKey){
        if (deleteKey==null)return false;
        int hash=hashCode(deleteKey);
        Entry<K,V> tmp=table[hash];
        Entry<K,V> previous=tmp;
        while (tmp!=null){
            if (deleteKey.equals(tmp.key)){
                if (previous==tmp)
                    table[hash]=tmp.next;
                else
                    previous.next=tmp.next;
                return true;
            }else {
                previous=tmp;
                tmp=tmp.next;
            }
        }
        return false;
    }

    public void display(){
        for (int i=0;i<capacity;i++){
            Entry<K,V> entry=table[i];
            while (entry!=null) {
                System.out.print("{" + entry.key + "=" + entry.value + "} ");
                entry=entry.next;
            }
        }
    }

    public int hashCode(K key) {
        return Math.abs(key.hashCode())%capacity;
    }
}
