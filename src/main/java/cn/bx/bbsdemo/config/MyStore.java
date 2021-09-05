package cn.bx.bbsdemo.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


//用k v的map存储key,后期可以存到数据库或者redis
@Component
public class MyStore {
    private static Map<String,Object> store=new HashMap<>();

    public Object get(String key){
        return store.get(key);
    }
    public Object remove(String key){
        return store.remove(key);
    }
    public void set(String key,Object o){
        store.put(key,o);
    }
    public boolean hasKey(String key){
        return store.containsKey(key);
    }
}
