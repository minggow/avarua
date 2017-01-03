package com.mingguo.avarua.casual.account.test.common.mess.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by mingguo.wu on 2017/1/3.
 */
public class TestNIO {

    public static void main(String[] args) {
        copy();
    }

    public static void copy() {
        String source="d:\\迅雷下载\\CentOS-6.5-x86_64-bin-DVD1.iso";
        String dest="d:\\CentOS-6.5-x86_64-bin-DVD1.iso";

        try(
                FileInputStream inputStream= new FileInputStream(source);
                FileOutputStream outputStream=new FileOutputStream(dest)) {
            FileChannel iChannel=inputStream.getChannel();
            FileChannel oChannel=outputStream.getChannel();

            ByteBuffer buffer= ByteBuffer.allocate(1024);
            long l1=System.currentTimeMillis();
            while(true){
                buffer.clear();//pos=0,limit=capcity，作用是让ichannel从pos开始放数据
                int r=iChannel.read(buffer);
                if(r==-1)//到达文件末尾
                    break;
                buffer.limit(buffer.position());//
                buffer.position(0);//这两步相当于 buffer.flip();
                oChannel.write(buffer);//它们的作用是让ochanel写入pos - limit之间的数据

            }
            inputStream.close();
            outputStream.close();
            System.out.println("complete:"+(System.currentTimeMillis()-l1));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
