package robedpixel.sdl.haptic.effect;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.ValueLayout;

public class SdlHapticEffectMemoryLayout {
  public static MemoryLayout layout =
      MemoryLayout.unionLayout(
          ValueLayout.JAVA_SHORT.withName("type"),
          SdlHapticConstantEffect.objectLayout.withName("constant"),
          SdlHapticPeriodicEffect.objectLayout.withName("periodic"),
          SdlHapticConditionEffect.objectLayout.withName("condition"),
          SdlHapticRampEffect.objectLayout.withName("ramp"),
          SdlHapticLeftRightEffect.objectLayout.withName("leftright"),
          SdlHapticCustomEffect.baseLayout.withName("custom"));
}
