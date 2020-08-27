import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {


    private static final Gson byteArray2Base64Gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new com.ilotterytech.common.utils.gson.ByteArray2Base64Adapter()).disableHtmlEscaping().create();

    private static final Gson prettyFormatedGson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
            new com.ilotterytech.common.utils.gson.ByteArray2Base64Adapter()).disableHtmlEscaping().setPrettyPrinting().create();


    public static Gson getGson() {

        return byteArray2Base64Gson;
    }

    public static Gson getPrettyFormatedGson() {

        return prettyFormatedGson;
    }





}