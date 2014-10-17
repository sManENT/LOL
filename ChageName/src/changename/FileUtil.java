/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package changename;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * @author Allen
 */
public class FileUtil {

    public static String sufNameFormat(String sufName) {
        if(sufName.indexOf(".") == 0) {
            return sufName;
        }
        return "." + sufName;
    }

    public static String pathFormat(String path) {
        int length = path.length();
        String lastString = path.substring(length-2, length-1);
        if (lastString.equalsIgnoreCase("\\") || lastString.equalsIgnoreCase("/")) {
            return path;
        }

        return path + "\\";
    }

    /**
     * 使用文件通道的方式复制文件
     *
     * @param s
     *            源文件
     * @param t
     *            复制到的新文件
     */
    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
