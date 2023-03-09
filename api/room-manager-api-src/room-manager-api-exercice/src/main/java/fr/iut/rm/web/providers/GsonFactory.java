package fr.iut.rm.web.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Factory used to obtain Gson object
 */
public final class GsonFactory {

    /**
     * GSON tool
     */
    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        gson = gsonBuilder.create();
    }

    /**
     * Disables constructors
     */
    private GsonFactory() {
    }

    /**
     * @return gson object
     */
    public static Gson getGson() {
        return gson;
    }

}
