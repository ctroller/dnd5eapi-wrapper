package ch.trox.dnd5eapi.internal;

import com.google.common.flogger.FluentLogger;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.annotation.Nullable;

import kong.unirest.Cache;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestParsingException;

/**
 * REST Client Helper
 *
 * <p>Takes care of initialization and providing helper methods to ease access for endpoint
 * implementations</p>
 */
public class RestHelper {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * The base URL to access any of the offered API endpoints. All implemented endpoints base on
     * this URL.
     */
    public static final String BASE_URL = "https://www.dnd5eapi.co/api";

    public static void init() {
        Unirest.config()
                .addDefaultHeader("User-Agent", "ch.trox.DnD5eApiWrapper")
                .defaultBaseUrl(BASE_URL)
                .cacheResponses(Cache.builder()
                        .maxAge(5, TimeUnit.MINUTES));
    }


    /**
     * Calls given {@code endpoint} over HTTP with a Json datatype and applies {@code converter} to
     * the result if applicable.
     *
     * <p>In case of an error, the result is logged and {@code null} is returned.</p>
     *
     * @param endpoint  the endpoint to call
     * @param converter the converter to apply to the returned json
     * @param <T>       the type of object to return
     * @return the converted object or {@code null} if endpoint doesn't return a valid status code.
     */
    @Nullable
    public static <T> T convertOrLog(String endpoint, Function<JsonNode, T> converter) {
        var response = Unirest.get(endpoint)
                .asJson();
        if (response.isSuccess()) {
            return response.map(converter)
                    .getBody();
        } else {
            logger.atWarning()
                    .log("Unable to look up entity.%n%s", response.getParsingError()
                            .map(UnirestParsingException::getOriginalBody)
                            .orElse(""));
        }

        return null;
    }
}
