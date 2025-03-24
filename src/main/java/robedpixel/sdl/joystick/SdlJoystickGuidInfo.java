package robedpixel.sdl.joystick;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SdlJoystickGuidInfo {
  private short vendor;
  private short product;
  private short version;
  private short crc16;
}
