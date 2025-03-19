package robedpixel.sdl.haptic.effect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticConditionEffect implements SdlHapticEffect {
  static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_SHORT.withName("type"),
              SdlHapticDirection.objectLayout.withName("direction"),
              ValueLayout.JAVA_INT.withName("length"),
              ValueLayout.JAVA_SHORT.withName("delay"),
              ValueLayout.JAVA_SHORT.withName("button"),
              ValueLayout.JAVA_SHORT.withName("interval"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("right_sat"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("left_sat"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("right_coeff"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("left_coeff"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("deadband"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("center"))
          .withName("SDL_HapticCondition");
  private MemorySegment segment;
  private Arena allocator = Arena.ofAuto();
  @Getter private SdlHapticType type;
  @Getter private SdlHapticDirection direction;
  @Getter private int length;
  @Getter private short delay;
  @Getter private short button;
  @Getter private short interval;
  @Getter private final short[] rightSat = new short[3];
  @Getter private final short[] leftSat = new short[3];
  @Getter private final short[] rightCoeff = new short[3];
  @Getter private final short[] leftCoeff = new short[3];
  @Getter private final short[] deadband = new short[3];
  @Getter private final short[] center = new short[3];
  private static final VarHandle typeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle directionHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("direction"));
  private static final VarHandle lengthHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("length"));
  private static final VarHandle delayHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("delay"));
  private static final VarHandle buttonHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("button"));
  private static final VarHandle intervalHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("interval"));

  private static final VarHandle rightSatHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("right_sat"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle leftSatHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("left_sat"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle rightCoeffHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("right_coeff"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle leftCoeffHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("left_coeff"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle deadbandHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("deadband"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle centerHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("condition"),
          MemoryLayout.PathElement.groupElement("center"),
          MemoryLayout.PathElement.sequenceElement());

  SdlHapticConditionEffect(SdlHapticConditionEffectBuilder builder) {
    this.type = builder.type;
    this.direction = builder.direction;
    this.length = builder.length;
    this.delay = builder.delay;
    this.button = builder.button;
    this.interval = builder.interval;
    System.arraycopy(builder.rightSat, 0, this.rightSat, 0, this.rightSat.length);
    System.arraycopy(builder.leftSat, 0, this.leftSat, 0, this.leftSat.length);
    System.arraycopy(builder.rightCoeff, 0, this.rightCoeff, 0, this.rightCoeff.length);
    System.arraycopy(builder.leftCoeff, 0, this.leftCoeff, 0, this.leftCoeff.length);
    System.arraycopy(builder.deadband, 0, this.deadband, 0, this.deadband.length);
    System.arraycopy(builder.center, 0, this.center, 0, this.center.length);
    createMemorySegment();
    ;
  }

  private void createMemorySegment() {
    segment = allocator.allocate(SdlHapticEffectMemoryLayout.layout);
    if (type != null) {
      typeHandle.set(segment, 0, type);
    }
    if (direction != null) {
      directionHandle.set(segment, 0, direction);
    }
    lengthHandle.set(segment, 0, direction);
    delayHandle.set(segment, 0, delay);
    buttonHandle.set(segment, 0, button);
    intervalHandle.set(segment, 0, interval);
    for (int i = 0; i < rightSat.length; i++) {
      rightSatHandle.set(segment, 0, i, rightSat[i]);
    }
    for (int i = 0; i < leftSat.length; i++) {
      leftSatHandle.set(segment, 0, i, leftSat[i]);
    }
    for (int i = 0; i < rightCoeff.length; i++) {
      rightCoeffHandle.set(segment, 0, i, rightCoeff[i]);
    }
    for (int i = 0; i < leftCoeff.length; i++) {
      leftCoeffHandle.set(segment, 0, i, leftCoeff[i]);
    }
    for (int i = 0; i < deadband.length; i++) {
      deadbandHandle.set(segment, 0, i, deadband[i]);
    }
    for (int i = 0; i < center.length; i++) {
      centerHandle.set(segment, 0, i, center[i]);
    }
  }

  @Override
  public MemorySegment getMemorySegment() {
    return segment;
  }
}
