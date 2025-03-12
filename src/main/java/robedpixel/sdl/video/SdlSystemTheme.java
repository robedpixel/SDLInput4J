package robedpixel.sdl.video;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Getter;
import robedpixel.sdl.power.SdlPowerState;

import java.util.List;

public enum SdlSystemTheme {

    /**< Unknown system theme */
    SDL_SYSTEM_THEME_UNKNOWN(0),
    /**< Light colored system theme */
    SDL_SYSTEM_THEME_LIGHT(1),
    /**< Dark colored system theme */
    SDL_SYSTEM_THEME_DARK(2);

    /** < Plugged in, battery charged */
    @Getter
    private final int value;

    SdlSystemTheme(final int value) {
        this.value = value;
    }

    private static final ImmutableMap<Integer, SdlSystemTheme> reverseLookup =
            Maps.uniqueIndex(List.of(SdlSystemTheme.values()), SdlSystemTheme::getValue);

    public static SdlSystemTheme fromInt(final int id) {
        return reverseLookup.get(id);
    }
}
