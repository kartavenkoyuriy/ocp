package chapter7.threadingProblems;

class Criminal {
    private boolean hostageReleased = false;

    void releaseHostage(Police police) {
        while (!police.isRansomGiven()) {
            System.out.println("Criminal: waiting for police to get ransom");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Criminal: got ransom, release hostage");
        hostageReleased = true;
    }

    public boolean isHostageReleased() {
        return hostageReleased;
    }
}

class Police {
    private boolean ransomGiven = false;

    void giveRansom(Criminal criminal){
        while (!criminal.isHostageReleased()){
            System.out.println("Police: waiting for criminal to release hostage");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Police: released hostage, give ransom");
        ransomGiven = true;
    }

    public boolean isRansomGiven() {
        return ransomGiven;
    }
}

public class LivelockCommon {
    //A thread often acts in response to the action of another thread. If the other thread's action is also a response to
    //the action of another thread, then livelock may result. As with deadlock, livelocked threads are unable to make
    //further progress. However, the threads are not blocked — they are simply too busy responding to each other to resume work.
    //
    //!unable to make further progress
    //
    //This is comparable to two people attempting to pass each other in a corridor:
    //Alphonse moves to his left to let Gaston pass, while Gaston moves to his right to let Alphonse pass.
    //Seeing that they are still blocking each other, Alphonse moves to his right, while Gaston moves to his left.
    //They're still blocking each other, so...
    public static void main(String[] args) {

    }

    //a criminal kidnaps a hostage and he asks for ransom in order to release the hostage.
    //A police agrees to give the criminal the money he wants once the hostage is released.
    //The criminal releases the hostage only when he gets the money.
    //Both are waiting(while ping each other) for each other to act first, hence livelock.
    private static void simpleLivelockExample() {
        Police police = new Police();
        Criminal criminal = new Criminal();

        new Thread(() -> police.giveRansom(criminal)).start();
        new Thread(() -> criminal.releaseHostage(police)).start();
    }
}
