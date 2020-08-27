import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.sql.*;
import java.util.*;




public class PathStandardFromTag
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        PathStandardFromTag pathStandardFromTag = new PathStandardFromTag();
        Set<String> names = pathStandardFromTag.conn();

        for(String name : names){
            CustomDictionary.add(name);
        }



        String path = "北京市房山区房山区河北镇 河东村";



        List<String> listTarget = pathStandardFromTag.splitFromString(path);//已存的地址
        pathStandardFromTag.statistic(listTarget);
     }



    public  Set<String>  conn() throws SQLException, ClassNotFoundException {
        Set<String> set = new HashSet<>();
        String URL = "jdbc:mysql://192.168.110.21/spider_temp";//?characterEncoding=utf-8
        String USER = "spider";
        String PASSWORD = "spider1234";
        PreparedStatement statement = null;
        Connection conn = null;
        ResultSet rs = null;

        // 1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn =  DriverManager.getConnection(URL, USER, PASSWORD);

            String sql="select type_name from t_type_info "; //where type_name not like '%第_社区%' ";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();


            while (rs.next()) {
                String type_name =  rs.getString("type_name");

                    set.add(type_name);

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

    private  Set<String> statistic(List<String> sentWords) {

        Set<String> frequencyOfWords = new HashSet<>();

        for (int i = 0; i < sentWords.size(); i++) {

            System.out.println(sentWords.get(i) );
            frequencyOfWords.add(sentWords.get(i));
        }

        return frequencyOfWords;

    }



}