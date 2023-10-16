import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Client {
    public String connectService(String webURL) {
        //假设服务使用可变的“message”来接收消息。
        String sql = "select * from people";
        String myRequest = "want=query&info=" + sql;

        try {

            //创建带有Web服务地址的URL实例
            URL url = new URL(webURL);

            //连接到Web服务
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置Post服务请求模式。
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //发送请求
            conn.getOutputStream().write(myRequest.getBytes(StandardCharsets.UTF_8));

            //从服务部门得到答复
            conn.getInputStream();
            InputStream content = conn.getInputStream();

            //将输入字符串转换为字节数组
            byte[] buf = new byte[1024];
            ByteArrayOutputStream sb = new ByteArrayOutputStream();
            int i;
            while ((i = content.read(buf)) != -1) {
                sb.write(buf, 0, i);
            }

            //关闭Web服务连接
            content.close();

            return sb.toString("UTF-8");
        } catch (IOException ex) {
            return null;
        }
    }

    public static void main(String[] args) {

        Client client = new Client();

        //服务的本地主机地址
        String myURL1 = "http://localhost:1111/Gate";
        String response1 = client.connectService(myURL1);
        System.out.println(myURL1 + "\n");
        System.out.println(response1);
    }
}