//package utils;
//
//import com.alibaba.fastjson.JSON;
//import okhttp3.*;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class OkHttpUtils {
//
//    private static OkHttpClient client = new OkHttpClient();
//    /**
//     * 发送post请求
//     *
//     * @param url url
//     * @param pojo bean
//     * @return 请求结果
//     */
//    public static <T> String post(String url, T pojo) {
//        HashMap<String, String> body = (HashMap<String, String>) JSON.toJSON(pojo);
//        return post(url, body);
//    }
//
//    /**
//     * 发送post请求
//     *
//     * @param url url
//     * @param params 参数
//     * @return 请求结果
//     */
//    public static String post(String url, Map<String, String> params) {
//        FormBody.Builder formBody = new FormBody.Builder();
//
//        if (params != null) {
//            for (Map.Entry<String, String> entries : params.entrySet()) {
//                formBody.add(entries.getKey(), entries.getValue());
//            }
//        }
//
//        Request request = new Request.Builder()
//                .url(url)
//                .method("POST", formBody.build())
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    /**
//     * 发送get请求
//     *
//     * @param url url
//     * @param params 参数
//     * @return 请求结果
//     */
//    public static String get(String url, Map<String, String> params) {
//        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
//
//        if (params != null) {
//            for (Map.Entry<String, String> param : params.entrySet()) {
//                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
//            }
//        }
//
//        Request request = new Request.Builder()
//                .url(httpBuilder.build())
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//}
