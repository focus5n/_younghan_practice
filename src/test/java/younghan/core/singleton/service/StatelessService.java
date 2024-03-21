package younghan.core.singleton.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatelessService {
    
    // 특정 클라이언트가 변경할 수 있는 필드가 존재해서는 안된다.

    // 값을 바로 반환하거나, ThreadLocal 사용해라.
    public Map<String, Object> order(String name, int price) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("name", name);
        map.put("price", price);

        System.out.println("name = " + map.get("name"));
        System.out.println("price = " + map.get("price") + "\n");
        
        return map;
    }
}
