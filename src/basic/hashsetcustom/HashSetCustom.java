package basic.hashsetcustom;

/**
 * Created by siege on 2017/8/14.
 */
public class HashSetCustom<E> {
    private HashMapCustom<E,Object> hashMapCustom;

    public HashSetCustom() {
        this.hashMapCustom = new HashMapCustom<>();
    }

    public void add(E value){
        hashMapCustom.put(value,null);
    }

    public boolean contains(E obj){
        return hashMapCustom.contains(obj)!=null;
    }

    public void display(){
        hashMapCustom.displaySet();
    }

    public boolean remove(E obj){
        return hashMapCustom.remove(obj);
    }
}
