package lucky.baijunhan.ex.netty.t;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class T0 {
    public static void main(String[] args) {
        Charset utf8 = StandardCharsets.UTF_8;
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf sliced = buf.slice(0,15);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0,'J');
        assert buf.getByte(0) == sliced.getByte(0);



    }
}
