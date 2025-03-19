package robedpixel.sdl.haptic.effect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.haptic.SdlHapticType;

class SdlHapticLeftRightEffect implements SdlHapticEffect {
  static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_SHORT.withName("type"),
              ValueLayout.JAVA_INT.withName("length"),
              ValueLayout.JAVA_SHORT.withName("large_magnitude"),
              ValueLayout.JAVA_SHORT.withName("small_magnitude"))
          .withName("SDL_HapticLeftRight");
  MemorySegment segment;
  private final Arena allocator = Arena.ofAuto();
  @Getter private SdlHapticType type;
  @Getter private int length;
  @Getter private short largeMagnitude;
  @Getter private short smallMagnitude;
  private static final VarHandle typeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("leftright"),
          MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle lengthHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("leftright"),
          MemoryLayout.PathElement.groupElement("length"));
  private static final VarHandle largeMagnitudeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("leftright"),
          MemoryLayout.PathElement.groupElement("large_magnitude"));
  private static final VarHandle smallMagnitudeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("leftright"),
          MemoryLayout.PathElement.groupElement("small_magnitude"));

  SdlHapticLeftRightEffect(SdlHapticLeftRightEffectBuilder builder) {
    this.type = builder.type;
    this.length = builder.length;
    this.largeMagnitude = builder.largeMagnitude;
    this.smallMagnitude = builder.smallMagnitude;
    createMemorySegment();
    ;
  }

  private void createMemorySegment() {
    segment = allocator.allocate(SdlHapticEffectMemoryLayout.layout);
    if (type != null) {
      typeHandle.set(segment, 0, type);
    }
    lengthHandle.set(segment, 0, length);
    largeMagnitudeHandle.set(segment, 0, largeMagnitude);
    smallMagnitudeHandle.set(segment, 0, smallMagnitude);
  }

  @Override
  public MemorySegment getMemorySegment() {
    return segment;
  }
}
