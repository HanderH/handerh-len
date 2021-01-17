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
public class NioDemo {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("/home/handerh/Documents/demoDir/demo.txt"));

        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(new File("/home/handerh/Documents/demoDir/demo2.txt"));

        FileChannel outChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        while((inChannel.read(byteBuffer)!=-1)){
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
