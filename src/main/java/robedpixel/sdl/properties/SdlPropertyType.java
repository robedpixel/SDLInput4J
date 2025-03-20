package robedpixel.sdl.properties;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlPropertyType {
  SDL_PROPERTY_TYPE_INVALID,
  SDL_PROPERTY_TYPE_POINTER,
  SDL_PROPERTY_TYPE_STRING,
  SDL_PROPERTY_TYPE_NUMBER,
  SDL_PROPERTY_TYPE_FLOAT,
  SDL_PROPERTY_TYPE_BOOLEAN
}
