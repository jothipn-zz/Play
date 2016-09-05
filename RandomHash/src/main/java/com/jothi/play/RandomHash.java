package com.jothi.play;

import java.util.Hashtable;
import java.util.Random;

public class RandomHash<K,V> extends Hashtable<K,V>
{
    private Integer counter = 0;
    private Hashtable<Integer, K> counterToKeyMap = new Hashtable();
    private Hashtable<K,ValueWrapper<V>> internalHashtable = new Hashtable();
    private Random r = new Random();

    public static void main( String[] args )
    {
        RandomHash<Integer,Integer> rh = new RandomHash();
        for (int i = 0; i < 10; i++) {
            rh.put(i,i);
        }

        for (int i = 0; i < 10; i++) {
            if (rh.get(i) != i)
                System.out.println("Error...");
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("Iter " + i + ", Value: " + rh.getRandom());
        }
    }

    RandomHash() {}

    @Override
    public synchronized V get(Object key) {
        return internalHashtable.get(key).value;
    }

    @Override
    public synchronized V put(K key, V value) {
        counter ++;
        counterToKeyMap.put(counter, key);
        internalHashtable.put(key, new ValueWrapper(counter, value));
        return value;
    }

    public synchronized V getRandom() {
        int tmp = r.nextInt(counter);
        while (counterToKeyMap.containsKey(tmp) == false) {
            tmp = r.nextInt(counter);
        }
        return internalHashtable.get(counterToKeyMap.get(tmp)).value;
    }

    @Override
    public synchronized V remove(Object key) {
        ValueWrapper<V> w = internalHashtable.remove(key);
        if (w != null) {
            counterToKeyMap.remove(w.counter);
            return w.value;
        }
        return null;
    }

    private class ValueWrapper<V> {
        final Integer counter;
        final V value;

        ValueWrapper(Integer counter, V value) {
            this.counter = counter;
            this.value = value;
        }
    }
}
