import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileOperation {
    public static void wirteFile(String path,String content){
        // true 表示追加写入
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path,true);
            fileOutputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void wirteFileTemperature(String path) throws IOException {
        // true 表示追加写入
        FileOutputStream fileOutputStream = null;

            int end = 0;
            while (end<1000) {
                //构造天气文件 2010,2,2,14  year,month.day,temperature
                Random random = new Random();
                int year = random.nextInt(10) + 2000;
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1;
                //round只能取到整数
                double temperature = Math.round(random.nextDouble() * 3000) / 100.0;
                StringBuilder line = new StringBuilder();
                line.append(year);
                line.append(',');
                line.append(month);
                line.append(',');
                line.append(day);
                line.append(',');
                line.append(temperature);
                line.append("\n");
                fileOutputStream = new FileOutputStream(path,true);
                fileOutputStream.write(line.toString().getBytes());
                end++;
            }
            fileOutputStream.close();
        System.out.println("complete!!!");

    }
    public static void main(String[] args) {
        try {
            wirteFileTemperature("/home/lz/temperature");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
