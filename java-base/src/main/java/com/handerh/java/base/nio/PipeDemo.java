package com.handerh.java.base.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author handerh
 * @date 2021/01/20
 */
public class PipeDemo {

    public static void main(String[] args) throws IOException {

        Pipe pipe = Pipe.open();

        Pipe.SourceChannel source = pipe.source();

        Pipe.SinkChannel sink = pipe.sink();

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        String str = "Hello World Pipe Message Thread";
        allocate.put(str.getBytes());

        while (source.read(allocate)!=-1){
            allocate.flip();
            sink.write(allocate);
        }
        System.out.println(sink.toString());
    }
}
