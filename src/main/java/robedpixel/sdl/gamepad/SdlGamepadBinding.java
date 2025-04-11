package robedpixel.sdl.gamepad;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.ValueLayout;

public class SdlGamepadBinding {
  public static final MemoryLayout objectLayout =
      MemoryLayout.structLayout(
          ValueLayout.JAVA_INT.withName("input_type"),
          MemoryLayout.unionLayout(
                  ValueLayout.JAVA_INT.withName("button"),
                  MemoryLayout.structLayout(
                          ValueLayout.JAVA_INT.withName("axis"),
                          ValueLayout.JAVA_INT.withName("axis_min"),
                          ValueLayout.JAVA_INT.withName("axis_max"))
                      .withName("axis"),
                  MemoryLayout.structLayout(
                          ValueLayout.JAVA_INT.withName("hat"),
                          ValueLayout.JAVA_INT.withName("hat_mask"))
                      .withName("hat"))
              .withName("input"),
          ValueLayout.JAVA_INT.withName("output_type"),
          MemoryLayout.unionLayout(
                  ValueLayout.JAVA_INT.withName("button"),
                  MemoryLayout.structLayout(
                          ValueLayout.JAVA_INT.withName("axis"),
                          ValueLayout.JAVA_INT.withName("axis_min"),
                          ValueLayout.JAVA_INT.withName("axis_max"))
                      .withName("axis"))
              .withName("output"));
}
