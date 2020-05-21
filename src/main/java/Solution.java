import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zcool on 2020/1/9.
 */
public class Solution {
    public static void main(String[] args) {
        String str = "https://www.zcool.com.cn/work/ZNDI2Mzc0NTY=.html";
        //定义正则表达式
        String reg = "(?<=https://www.zcool.com.cn/([a-zA-Z]{1,20}/)).*?(?=.html)";

        //编译正则表达式
        Pattern patten = Pattern.compile(reg);

        // 指定要匹配的字符串
        Matcher matcher = patten.matcher(str);

        List<String> matchStrs = new ArrayList<>();

        //此处find（）每次被调用后，会偏移到下一个匹配
        while (matcher.find()) {

            //获取当前匹配的值
            matchStrs.add(matcher.group());
        }

        for (int i = 0; i < matchStrs.size(); i++) {
            System.out.println(matchStrs.get(i));
        }

        }


}
