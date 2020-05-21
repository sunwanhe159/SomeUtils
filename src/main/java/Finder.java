import java.io.File;
import java.util.Scanner;

/**小孙IO训练记。
 * Created by zcool on 2020/4/16.
 */
public class Finder {
    public static void main(String args[]){

        //获取路径;
        System.out.println("请输入路径：\n");
        Scanner input = new Scanner(System.in);
        String str = input.next();

        //判断是否存在，若存在则循环输出。
        File file = new File(str);
        if (!file.exists()) {
            System.out.println("该文件夹不存在。");
        }else {
            listAll(file,0);
        }

    }

    private static void listAll(File file,int level) {

        System.out.println(getSapce(level)+file.getName());

        //获取指定目录下，所有的文件对象和文件名
        File[] f = file.listFiles();
        level++;
        for(File ff : f){

            //判断ff是否是目录。
            if(ff.isDirectory()){
                listAll(ff,level);
            }else {
                System.out.println(getSapce(level) + ff.getName());
            }
        }
    }

    public static String getSapce(int level){
        StringBuilder sb = new StringBuilder();
        for(int x=0;x<level;x++){
            sb.append("|---->");
        }
        return sb.toString();
    }
}
