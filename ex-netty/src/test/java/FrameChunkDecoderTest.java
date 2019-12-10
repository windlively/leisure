import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import lucky.baijunhan.ex.netty.FrameChunkDecoder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameChunkDecoderTest {

    @Test
    public void testFrameDecoded(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            channel.writeInbound(input.readBytes(4));
            Assert.fail();
        }catch (TooLongFrameException ignored){
            System.out.println(ignored.toString());
        }
        assertTrue(channel.writeInbound(input.readBytes(3)));
        assertTrue(channel.finish());

        ByteBuf read = channel.readInbound();
        for (int i = 0; i < read.readableBytes(); i++) {
            System.out.print(read.getByte(i) + " ");
        }
        assertEquals(buf.readSlice(2), read);
        read.release();
        System.out.println();
        read = channel.readInbound();
        for (int i = 0; i < read.readableBytes(); i++) {
            System.out.print(read.getByte(i) + " ");
        }
        assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();
        buf.release();
    }
}
