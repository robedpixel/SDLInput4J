package robedpixel.sdl.guid;

import java.io.UnsupportedEncodingException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.Charset;
import lombok.Getter;

// TODO: modify SdlGuid to use this class for methods
public class SdlGuidByteArray {
  private final byte[] data;
  @Getter private final MemorySegment dataAddress;
  private final Arena allocator = Arena.ofAuto();

  public SdlGuidByteArray() {
    data = new byte[33];
    dataAddress = allocator.allocateFrom(ValueLayout.JAVA_BYTE, data);
  }

  public SdlGuidByteArray(int size) {
    if (size < 33) {
      throw new IllegalArgumentException("Size cannot be smaller than 33!");
    }
    data = new byte[size];
    dataAddress = allocator.allocateFrom(ValueLayout.JAVA_BYTE, data);
  }

  public SdlGuidByteArray(byte[] array) {
    if (array.length < 33) {
      throw new IllegalArgumentException("Size cannot be smaller than 33!");
    }
    data = array.clone();
    dataAddress = allocator.allocateFrom(ValueLayout.JAVA_BYTE, data);
  }

  public byte getData(int index) {
    return data[index];
  }

  public void setData(int index, byte value) {
    data[index] = value;
    dataAddress.setAtIndex(ValueLayout.JAVA_BYTE, index, value);
  }

  public int getArrayLength() {
    return data.length;
  }

  public String getStringFromByteArray(Charset charset) throws UnsupportedEncodingException {
    return new String(data, charset);
  }
}
