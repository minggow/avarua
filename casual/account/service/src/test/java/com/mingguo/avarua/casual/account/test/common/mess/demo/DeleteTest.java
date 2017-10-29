package com.mingguo.avarua.casual.account.test.common.mess.demo;

import org.junit.Test;

import org.junit.Test;

import java.io.File;

/**
 * Created by mingguo.wu on 2015/12/17.
 */
public class DeleteTest {
    private static int counter = 0;

    @Test
    public void testDeleteFile() {
        String fileName = "D:\\tmp2\\trunk_2";
        handleFile(new File(fileName));
        ///dddd
    }

    private static void handleFile(File f) {
        if (f.exists()) {
            File[] files = f.listFiles();
            if (files != null)
                for (File file : files)
                    if (file.getName().contains(".svn")) //这里换成正则表达式亦可，删除符合条件的文件或文件夹
                        deleteAllFiles(file);
                    else //假如当前目录下没有svn文件夹，查看子目录是否有
                        handleFile(file);
        }
    }

    private static void deleteAllFiles(File f) {
        if (f.exists()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files)
                    if (file.isDirectory()) {
                        deleteAllFiles(file);
                        file.delete(); //删除目录下的所有文件后，该目录变成了空目录，可直接删除
                        System.out.println("成功删除了 " + (++counter) + "，名字为： " + file.getName());
                    } else if (file.isFile()) {
                        file.delete();
                        System.out.println("成功删除了 " + (++counter) + "，名字为： " + file.getName());
                    }
            }
            f.delete(); //删除最外层的目录
            System.out.println("成功删除了 " + (++counter) + "，名字为： " + f.getName());
        }
    }
}
