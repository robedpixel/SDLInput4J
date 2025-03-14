package robedpixel.sdl.properties;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Getter;
import robedpixel.sdl.power.SdlPowerState;

import java.util.List;

public enum SdlPropertyType {
    SDL_PROPERTY_TYPE_INVALID(0),
    SDL_PROPERTY_TYPE_POINTER(1),
    SDL_PROPERTY_TYPE_STRING(2),
    SDL_PROPERTY_TYPE_NUMBER(3),
    SDL_PROPERTY_TYPE_FLOAT(4),
    SDL_PROPERTY_TYPE_BOOLEAN(5);
    @Getter
    private final int value;

    SdlPropertyType(final int value) {
        this.value = value;
    }

    private static final ImmutableMap<Integer, SdlPropertyType> reverseLookup =
            Maps.uniqueIndex(List.of(SdlPropertyType.values()), SdlPropertyType::getValue);

    public static SdlPropertyType fromInt(final int id) {
        return reverseLookup.get(id);
    }
}
