package fr.iut;

/**
    * Main entry point for demo
    */
public class Main {
    public static void main(String[] args) {
        DeprecatedAnnotationSample sample = new DeprecatedAnnotationSample();
        sample.deprecatedMethod();
        sample.nonDeprecatedMethod();
    }   
}
