package com.handerh.java.base.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author handerh
 * @date 2021/01/17
 */
public class ChannelDataTransfer {

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream(new File("/home/handerh/Documents/demoDir/demo.txt"));

        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(new File("/home/handerh/Documents/demoDir/demo2.txt"));

        FileChannel outChannel = fos.getChannel();

        inChannel.transferTo(0,inChannel.size(),outChannel);
    }
}
