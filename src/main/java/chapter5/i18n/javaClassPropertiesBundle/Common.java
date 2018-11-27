package chapter5.i18n.javaClassPropertiesBundle;

public class Common {
    //resource bundle looks from specific to the top level
    //Zoo_en_CA.properties -> Zoo_en.properties -> Zoo.properties

    //resource bundle looks
    //"full"class->"full"props->"only lang"class->"only lang"props->
    //"full default"class->"full default"props->"only lang default"class->"only lang default"props->
    //"only name"class->"only name"props->exception
}
