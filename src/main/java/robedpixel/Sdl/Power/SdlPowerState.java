package robedpixel.Sdl.Power;

import lombok.Getter;

public enum SdlPowerState {
    SDL_POWERSTATE_ERROR(-1),   /**< error determining power status */
    SDL_POWERSTATE_UNKNOWN(0),      /**< cannot determine power status */
    SDL_POWERSTATE_ON_BATTERY(1),   /**< Not plugged in, running on the battery */
    SDL_POWERSTATE_NO_BATTERY(2),   /**< Plugged in, no battery available */
    SDL_POWERSTATE_CHARGING(3),     /**< Plugged in, charging battery */
    SDL_POWERSTATE_CHARGED(4);       /**< Plugged in, battery charged */
    @Getter
    private final int value;
    SdlPowerState(int value){
        this.value = value;
    }
}
