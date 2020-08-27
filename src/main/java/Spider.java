
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Spider {
    private Logger logger = LoggerFactory.getLogger(Spider.class);
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build();

    private final long inteval = 100;
    public static void main(String[] args) throws IOException {
        Spider spider = new Spider();
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/11.html";
        String result = spider.doGet("");
//         GsonUtils.getGson().fromJson(result,SimilarCos.class);
         System.out.println(result);
    }
     private String doGet(String url) throws IOException {

        int retry = 3;
        String result = null;

        while (retry >= 0) {

            try {
                Thread.sleep(inteval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.debug("request:{}", url);

            try {

                Request request = new Request.Builder()//读界面信息的header头
                        .url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/11/1101.html")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("Host", "www.stats.gov.cn")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                        .get()
                        .build();

               Response response = client.newCall(request).execute();//执行返回response

                if (response.code() != 200) {

                    throw new IOException("status code is not 200");
                }

                result = response.body().string();
                response.close();

                break;
            } catch (IOException e) {

                logger.warn("failed to doGet, url:{}, error:{}", url, e);
                logger.warn("retry, url:{}", url);
                System.out.println("_------------------");
                retry--;
                continue;

            }
        }

        if (retry < 0) {
            throw new IOException("XXX");
        }

      return result;


    }

}
