package fr.iut;

/**
* This class declares a deprecated method.
*/
public class DeprecatedAnnotationSample {
    /**
     * @deprecated
     * explanation of why deprecatedMethod() was deprecated
     */
    @Deprecated
    public void deprecatedMethod() {
        System.out.println("calling deprecatedMethod is deprecated");
    }

    /**
     * explanation of nonDeprecatedMethod()
     */
    public void nonDeprecatedMethod() {
        System.out.println("calling nonDeprecatedMethod is well supported");
    }
}
