import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class hiUDF extends UDF {
    public Text evaluate (String input){
        return new Text("hello："+input);
    }
    public static void main(String[] args) {
        hiUDF udf = new hiUDF();
        Text t = udf.evaluate("zhangsan");
        System.out.println(t);
    }
}
