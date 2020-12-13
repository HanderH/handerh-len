package com.handerh.distrublock.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * @author handerh
 * @date 2020/12/10
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMock {

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    @Mock
    private List<String> list;

    @Test
    public void how_to_use_subbing(){
        //return时thenReturn和doReturn功能一样
        Mockito.when(list.get(0)).thenReturn("first");
        Mockito.doReturn("second").when(list).get(1);

        Assert.assertEquals("first", list.get(0));
        Assert.assertEquals("second", list.get(1));

        //有返回值throw时用thenThrow
        Mockito.when(list.get(Mockito.anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        }catch (Exception e){
//            Assert.assertThrows(RuntimeException.class,()->list.get(0));
        }
    }
    @Test
    public void subbing_with_answer(){
        Mockito.when(list.get(Mockito.anyInt())).thenAnswer(new Answer() {
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return (Integer) invocation.getArguments()[0]*10;
            }
        });
        Assert.assertEquals(list.get(2),20);
        Assert.assertEquals(list.get(88),880);
    }
}
