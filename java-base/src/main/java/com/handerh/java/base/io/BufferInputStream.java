package com.handerh.java.base.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author handerh
 * @date 2021/01/17
 */
public class BufferInputStream {

    public static void main(String[] args) throws IOException {
        try {
            // 初始化一个字节数组，内有10个字节的数据
            byte[] bytes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
            // 用一个ByteArrayInputStream来读取这个字节数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            // 将ByteArrayInputStream包含在一个BufferedInputStream
            BufferedInputStream bis = new BufferedInputStream(in, 10);

            //超出缓冲区，情景4
            bis.mark(11);
            //bis.mark(11);报错，请自己分析一下，很容易的

            int c;
            while ( (c = bis.read()) != -1) {
                System.out.print(c + ",");
            }

            System.out.println("\nreset");
            bis.reset();
            while ( (c = bis.read()) != -1) {
                System.out.print(c + ",");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
