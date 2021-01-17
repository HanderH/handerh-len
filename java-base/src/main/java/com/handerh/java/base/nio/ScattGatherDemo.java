package com.handerh.java.base.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author handerh
 * @date 2021/01/17
 */
public class ScattGatherDemo {

    public static void main(String[] args) throws Exception {

        // 聚集写入
        ByteBuffer buffer1 = ByteBuffer.allocate(8);
        ByteBuffer buffer2 = ByteBuffer.allocate(400);
        FileOutputStream fos = new FileOutputStream(new File("/home/handerh/Documents/demoDir/demo2.txt"));
        buffer1.asIntBuffer().put(999);
        buffer2.asCharBuffer().put("Hello World");
        FileChannel fosChannel = fos.getChannel();
        fosChannel.write(new ByteBuffer[]{buffer1, buffer2});
        // 分散读取
        FileInputStream fis = new FileInputStream(new File("/home/handerh/Documents/demoDir/demo2.txt"));
        FileChannel inChannel = fis.getChannel();
        ByteBuffer buffer3 = ByteBuffer.allocate(8);
        ByteBuffer buffer4 = ByteBuffer.allocate(400);
        inChannel.read(new ByteBuffer[]{buffer3,buffer4});
        buffer3.rewind();
        buffer4.rewind();

        System.out.println(buffer3.asIntBuffer().get());
        System.out.println(buffer4.asCharBuffer().toString());
    }

}