import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public class DemoCustomDictionary
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        DemoCustomDictionary demoCustomDictionary = new DemoCustomDictionary();
        Set<String> names = demoCustomDictionary.conn();
        CustomDictionary.add("新外大街");
        for(String name : names){
            CustomDictionary.add(name);
        }

        CustomDictionary.add("第一");

//        DemoCustomDictionary hanplDemo1 =  new DemoCustomDictionary();
        String str1 = "北京市门头沟区门头沟区清水镇塔河村";
        String str2 = "北京市门头沟区门头沟区清水镇塔河村";
        List<String> list1Source = demoCustomDictionary.splitFromString(str1);//要插的地址

        List<String> listTarget = demoCustomDictionary.splitFromString(str2);//已存的地址
        double similarity =  demoCustomDictionary.getSimilarity(list1Source,listTarget);
        System.out.println(similarity);
    }



    public  Set<String>  conn() throws SQLException, ClassNotFoundException {
        Set<String> set = new HashSet<>();
        String URL = "jdbc:mysql://192.168.110.21/spider_temp";//?characterEncoding=utf-8
        String USER = "spider";
        String PASSWORD = "spider1234";
        PreparedStatement statement = null;
        Connection conn = null;
        ResultSet  rs = null;

        // 1.加载驱动程序
        try {
              Class.forName("com.mysql.jdbc.Driver");

              conn =  DriverManager.getConnection(URL, USER, PASSWORD);

              String sql="select type_name from t_type_info "; //where type_name not like '%第_社区%' ";
              statement = conn.prepareStatement(sql);
              rs = statement.executeQuery();


            while (rs.next()) {
               String type_name =  rs.getString("type_name");
                if(type_name.contains("社区")){

                    int index =  type_name.indexOf("社区");

                    set.add( type_name.substring(0,index));
                    if(type_name.length()-2 == index){
                        set.add(type_name.substring(index));

                    }else{
                        set.add(type_name.substring(index,index+2));
                        set.add(type_name.substring(index+2));

                    }

                }else{
                    set.add(type_name);
                }
             }

            return set;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            try {
                rs.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

            try {
                statement.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList splitFromString(String str1) {
        ArrayList<String> list = new ArrayList<>();
        List<Term> listAsStr1=  HanLP.segment(str1);
        for(Term term:listAsStr1){

            list.add(term.word);

        }
        return  list;
    }

    private  int[] statistic(List<String> allWords, List<String> sentWords) {

        int[] result = new int[allWords.size()];
        Map<String,Integer> frequencyOfWords = new HashMap<>();

        for (int i = 0; i < allWords.size(); i++) {

            result[i] = Collections.frequency(sentWords, allWords.get(i)) ; // 用于统计指定对象在指定集合中出现的次数
             System.out.println(allWords.get(i)+"       "+result[i] +"  &&&&&");
             frequencyOfWords.put(allWords.get(i),result[i]);
        }

        return result;

    }


    private  List<String> mergeList(List<String> list1,List<String> list2) {

        List<String> result = new ArrayList<>();

        result.addAll(list1);

        result.addAll(list2);


        return  result.stream().distinct().collect(Collectors.toList());

    }
    public  double getSimilarity(List<String> sentence1, List<String> sentence2) {




        List<String> allWords = mergeList(sentence1, sentence2); //addAll   把这两个list的元素 去重后merge在一起


        int[] statistic1 = statistic(allWords, sentence1);// 单词出现的次数 1 1 1 1
        System.out.println("========================"); // 1 0 1 1
        int[] statistic2 = statistic(allWords, sentence2);


        double dividend = 0;

        double divisor1 = 0;

        double divisor2 = 0;

        for (int i = 0; i < statistic1.length; i++) {

            dividend += statistic1[i] * statistic2[i];//  相当于 a*b  向量a•向量b= x1x2+y1y2+z1z2......

            divisor1 += Math.pow(statistic1[i], 2);//|a|的平方

            divisor2 += Math.pow(statistic2[i], 2);// |b|的平方

        }
        System.out.println(dividend+"   "+divisor1+"   "+divisor2);

        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));  // cos=ab/|a|*|b| a,b是向量


    }


}