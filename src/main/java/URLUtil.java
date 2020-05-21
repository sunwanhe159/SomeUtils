import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一些小工具
 * Created by sunwh on 2020/4/28.
 */
public class URLUtil {
    /**
     * 预编译正则表达式。
     *
     */
    private static final Pattern URL_PATTERN = Pattern.compile("\"https://((static.zcool.cn)|(www.zcool.com.cn))/([\\w\\S]{1,100})?([\\w\\S]{1,30}\\.((png)|(gif)|(jpg)))");

    public static void main(String[] args) {

        try{
            // 从本地读取赛事和专题，然后仿放进数组里。
            String specialsPath = "/Users/zcool/Documents/specials.text";
            FileReader reader = new FileReader(specialsPath);
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            br.close();
            reader.close();
            String[] specials = str.split(",");

            // 拼接URL，然后从返回页面中匹配字符串输出。
            for (String special : specials) {
                String url = "https://www.zcool.com.cn/special/" + special;
                try{
                    Document doc = Jsoup.connect(url).get();
                    String uurl = doc.outerHtml();
                    System.out.println(special);
                    System.out.println("");

                    // 开始匹配正则表达式，然后输出。
                    outPutInfo(uurl);
                    System.out.println("");
                }catch (Exception e) {
                    System.out.println(special + " " + "error");
                    e.getStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
 }

    public static void outPutInfo(String s) {
        Pattern pattern = URL_PATTERN;
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }

    public static String getUrl (String url) {
        String result = "";
        BufferedReader read = null;
        try{
            URL realurl = new URL(url);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            // 循环读取
            String line;
            while ((line = read.readLine()) != null) {
                result += line;

        }
        }catch (Exception e) {
                e.printStackTrace();
            }finally{
                // 关闭流
                if(read!=null){
                    try {
                        read.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
    }

    public static String addPost(String inurl ) throws IOException{
        URL url=new URL(inurl);
        HttpURLConnection connection =(HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();

        //POST请求
        DataOutputStream out=new DataOutputStream(connection.getOutputStream());
        out.flush();
        out.close();
        //读取响应
        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        StringBuffer sb=new StringBuffer("");
        while((lines = reader.readLine())!=null){
            lines =new String(lines.getBytes(),"utf-8");
            sb.append(lines);
        }
        reader.close();
        connection.disconnect();
        return sb.toString();


}

}
