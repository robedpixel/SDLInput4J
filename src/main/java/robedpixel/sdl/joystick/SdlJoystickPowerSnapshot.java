package robedpixel.sdl.joystick;

import lombok.Getter;
import lombok.Setter;

public class SdlJoystickPowerSnapshot {
  /** Percentage of battery life left */
  @Getter @Setter int percent;

  /** State of joystick's power supply (See SdlPowerState) */
  @Getter @Setter int powerState;
}
