import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class MD5Demo {
    public static void main(String[] args) {
        String string = "北京市昌平区昌平区东小口地区店上村";
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        String len = "111101100000932782511072105600011110110000093278225";
        System.out.println(len.length());
        String s= DigestUtils.md5Hex(b);
        System.out.println(s);  // bc35363423f91a2286f9dd29f6182ce1
        if (true){}
        List<String> listTest = new ArrayList<>();
        listTest.add("1");
        listTest.add("2");
        listTest.add("3");
        listTest.add("4");
        List<String> subFromListTests = listTest.subList(0,2);
        subFromListTests.remove(1);
        for (String subFromListTest : subFromListTests){
            System.out.println(subFromListTest);
        }
        System.out.println("=======================");
        for(String list:listTest){
            System.out.println(list);
        }
        // while (true){}
        listTest.toArray(new String[listTest.size()]);

        String[] strings = new String[]{"1","2","3","4","5"};
        List<String> list = Arrays.asList(strings);
        System.out.println("========================");
        System.out.println(list.get(0));
       // list.add("6");  Arrays.asList体现的是适配器模式，只是转换接口，后台数据仍是数组

          List<String> list1 = new ArrayList<String>();
//        list1.add("1");
//        list1.add("2");
//
//        for (String item : list1) {
//            if ("2".equals(item)) {
//                list1.remove(item);
//            }
//
//        }

        // 最好用 enterSet 和  forEach方法来遍历hashMap，因为他们都是只遍历一次map集合，就可以得到所有的kv，
        // 而keySet需要办理两次Map集合，一次是转为Iterator，第二次是根据key获取value，效率低下
        Map<String,String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.forEach((k,v)->{
            System.out.println(k+"  "+v);
            list1.add(k);
        });
        for (String str : list1){
            System.out.println(str);
        }
    }
}
