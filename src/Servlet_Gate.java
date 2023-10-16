import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet("/Gate")
public class Servlet_Gate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost方法被调用！");
        String want = request.getParameter("want");
        String info = request.getParameter("info");
        System.out.println("Gate的doPost被调用");
        String message = "want=" + want + "&" + "info=" + info;
        System.out.println(message);
        switch (want){
            case "query":
                String replyFromHandle01 = sendMessageAndWaitForReply("http://localhost:2221/Handle01", message);
                writeIntoResponse(replyFromHandle01, response);
                break;
        }
    }

    // 将信息包装进response对象
    protected  HttpServletResponse writeIntoResponse(String info, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(info);
        }
        return response;
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

            return sb.toString("UTF-8");
        } catch (IOException ex) {
            return null;
        }
    }
}