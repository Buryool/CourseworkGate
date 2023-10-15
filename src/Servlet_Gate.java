import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet("/Gate")
public class Servlet_Gate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("doPost方法被调用！");
        String want = request.getParameter("want");
        String info = request.getParameter("info");
        System.out.println("Gate的doPost被调用");
        String message = "want=" + want + "&" + "info=" + info;
        System.out.println(message);
        switch (want){
            case "query":
                // TODO 这里要实现从Gate向客户端返回数据的功能
                sendMessageAndWaitForReply("http://localhost:2221/CourseworkHandle01_Web_exploded/Handle01", message);
                break;
        }
    }

    // 向指定URL发送指定信息
    public String sendMessageAndWaitForReply(String webURL, String message) {
        try {
            //创建带有Web服务地址的URL实例
            URL url = new URL(webURL);

            //连接到Web服务
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置Post服务请求模式。
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //发送请求
            conn.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));

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

            return sb.toString();
        } catch (IOException ex) {
            return null;
        }
    }
}