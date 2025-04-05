package robedpixel.sdl.mouse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SdlMouseState {
    private float x;
    private float y;
    private int mouseButtonFlags;
}
