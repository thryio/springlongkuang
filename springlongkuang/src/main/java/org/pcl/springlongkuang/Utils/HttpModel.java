package org.pcl.springlongkuang.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpModel {
    /**
     * 采用http协议get方式访问接口
     * @param urlpath 接口请求地址
     */
    public static String sendGet( String urlpath) {
        StringBuffer buffer = null;
        try {
            URL url = new URL(urlpath);

            // 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
            // (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("GET");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据
            // application/x-javascript->json对象
            // application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 建立连接
            // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            isr.close();
            connection.disconnect();// 销毁连接

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
