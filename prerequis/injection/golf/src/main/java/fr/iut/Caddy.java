package fr.iut;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * A caddy has several clubs and knows which club to use depending on conditions
 */
public class Caddy {
    @Inject
    @Named("Putter")
    private Club putter;

    @Inject
    @Named("Wood")
    private Club wood;
    /**
     * default empty constructor *
     */
    public Caddy() {  }

    /**
     * Return
     * @param conditions
     * @return
     */
    public Club getClub(final Conditions conditions) {
        switch (conditions) {
            case GREEN:
                return putter;
            case FAIRWAY:
                return wood;
            default:
                return putter;
        }
    }
}