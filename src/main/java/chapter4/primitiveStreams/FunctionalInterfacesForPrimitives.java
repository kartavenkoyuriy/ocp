package chapter4.primitiveStreams;

import java.util.function.BooleanSupplier;

public class FunctionalInterfacesForPrimitives {
    public static void main(String[] args) {
        //!!! primitive Supplier - getAs...
        //primitive Consumer - receive primitives
        //LongFunction - same simple
        //Predicate - same simple
        //!!! DoubleUnaryOperator/LongBinaryOperator - applyAs...

        //■ Generics are gone from some of the interfaces, since the type name tells us what primitive
        //type is involved. In other cases, such as IntFunction, only the return type generic is needed.
        //■ The single abstract method is often, but not always, renamed to reflect the primitive
        //type involved.
        //■ BiConsumer, BiPredicate, and BiFunction are not in Table 4.9. The API designers
        //stuck to the most common operations. For primitives, the functions with two different
        //type parameters just aren’t used often.



    }

    private static void booleanSuplierExample() {
        BooleanSupplier booleanSupplier = () -> Math.random() > .5;
        System.out.println(booleanSupplier.getAsBoolean());
        System.out.println(booleanSupplier.getAsBoolean());
        System.out.println(booleanSupplier.getAsBoolean());
        System.out.println(booleanSupplier.getAsBoolean());
        System.out.println(booleanSupplier.getAsBoolean());
    }
}
