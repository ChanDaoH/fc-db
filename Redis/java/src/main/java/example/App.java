package example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import redis.clients.jedis.Jedis;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.StreamRequestHandler;


public class App implements StreamRequestHandler{

    private String host = System.getenv("REDIS_HOST");
    private int port = 6379;
    private String passwd = System.getenv("REDIS_PASSWORD");

    @Override
    public void handleRequest(
            InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        byte[] bytes = new byte[0];
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes);
        System.out.println(str);
        String currentTime = "unavailable";
        try {
            Jedis jedis = new Jedis(host, port);
            //鉴权信息
            jedis.auth(passwd);//password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
       } 
       catch (Exception e) {
        e.printStackTrace();
        }

 

        outputStream.write(currentTime.getBytes());

    }

}
