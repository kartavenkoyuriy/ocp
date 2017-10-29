package chapter1;

/**
 * Created by Yuriy Kartavenko on 4/18/2017.
 */
public enum EnumExample {
    ONE, TWO
}

enum EnumComplexExample{
    //semicolon at the end of the enum values is optional only if the only thing in the enum is that list of values
    ELEVEN("eleven!"), TWELVE("twelve!");

    private String numberName;

    //private access modifier is implied!!!
    EnumComplexExample(String numberName) {
        this.numberName = numberName;
        System.out.println("only once");
    }

    //staticBlock modifier is implied
    public void simplePrint(){
        System.out.println("here come the " + this.numberName);
    }
}

enum AbstractEnumExample{
    WINTER{
        @Override
        public void getSeasonTemp() {
            System.out.println("cold" + this.name().length());
        }
    }, SPRING{
        @Override
        public void getSeasonTemp() {
            System.out.println("norm" + this.name().length());
        }
    }, SUMMER{
        @Override
        public void getSeasonTemp() {
            System.out.println("warm" + this.name().length());
        }
    }, FALL{
        @Override
        public void getSeasonTemp() {
            System.out.println("norm" + this.name().length());
        }

        @Override
        public void getDefaultSeasonDescription() {
            System.out.println("VERY GOOD");
        }
    };

    public abstract void getSeasonTemp();
    public void getDefaultSeasonDescription(){
        System.out.println("GOOD");
    }
}

class TestEnums{
    public static void main(String[] args) {
        //The first time that we ask for any of the enum values, Java constructs all of the enum values
        EnumComplexExample complexEnum10 = EnumComplexExample.ELEVEN;
        EnumComplexExample complexEnum11 = EnumComplexExample.ELEVEN;
        EnumComplexExample complexEnum20 = EnumComplexExample.TWELVE;
//        complexEnum = new EnumComplexExample()
        EnumComplexExample.TWELVE.simplePrint();

        AbstractEnumExample.WINTER.getSeasonTemp();
        AbstractEnumExample.SPRING.getDefaultSeasonDescription();
        AbstractEnumExample.FALL.getDefaultSeasonDescription();
    }
}
