package basic.linkedhashsetcustom;

/**
 * Created by siege on 2017/8/16.
 */
public class LinkedHashSetCustom<E> {
    private LinkedHashMapCustom<E, Object> linkedHashMapCustom;

    public LinkedHashSetCustom(){
        linkedHashMapCustom=new LinkedHashMapCustom<>();
    }

    /**
     * add objects in LinkedHashSetCustom.
     */
    public void add(E value){
        linkedHashMapCustom.put(value, null);

    }

    /**
     * Method returns true if LinkedHashSetCustom contains the object.
     * @param
     */
    public boolean contains(E obj){
        return linkedHashMapCustom.contains(obj) !=null ? true :false;
    }

    /**
     * Method displays all objects in LinkedHashSetCustom.
     * insertion order is not guaranteed, for maintaining insertion order refer LinkedHashSet.
     */
    public void display(){
        linkedHashMapCustom.displaySet();
    }

    /**
     * Method removes object from setCustom.
     * insertion order is not guaranteed, for maintaining insertion order refer LinkedHashSet.
     * @param obj
     */
    public boolean remove(E obj){
        return linkedHashMapCustom.remove(obj);
    }

}


