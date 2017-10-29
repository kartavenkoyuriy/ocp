package chapter2.composing;

//Object composition should be thought of as an alternate to inheritance and is often
//used to simulate polymorphic behavior that cannot be achieved via single inheritance.

//One of the advantages of object composition over inheritance is that it tends to promote
//greater code reuse. By using object composition, you gain access to other classes and
//methods that would be difficult to obtain via Java’s single‐inheritance model.

//Object composition may seem more attractive than inheritance because of its reusable
//nature, but bear in mind that one of the strengths of Java is its powerful inheritance
//model. Object composition still requires you to explicitly expose the underlying methods
//and values manually, whereas inheritance includes protected and public members
//automatically. Also, using method overloading to determine dynamically which method
//to select at runtime is an extremely powerful tool for building intelligent classes. In other
//words, both object composition and inheritance have their proper place in developing good
//code, and in many cases it may be diffcult to decide which path to choose.
public class Penguin {
    private Flippers flippers;
    private WebbedFeet webbedFeet;

    public Penguin(Flippers flippers, WebbedFeet webbedFeet) {
        this.flippers = flippers;
        this.webbedFeet = webbedFeet;
    }

    public void goFlipping(){
        this.flippers.toFlip();
    }

    public void goWebbFitting(){
        this.webbedFeet.toWebb();
    }
}
