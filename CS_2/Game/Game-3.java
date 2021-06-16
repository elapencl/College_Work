import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game {
    //The room the player is currently in
    private Room currentRoom;
    //The player object - resets to a new player with position at 0,0 when you move to a new room.
    private People player;
    //The array list containing all the points of interest for the current room.
    private List<PointOfInterest> points;
    //Set to true if the player is on a point of interest, false if not.
    private boolean isPlayerOnAPoint;
    //Yields an int which corresponds to the "order" variable of whatever point of interest the player is on.
    private int whatPoint;
    //Int which gets set to 1, 2, or 3, depending on what the player chooses while at a point of interest. Starts at 0.
    private int selection;
    //Set to true if player is on an exit, false if not. Starts at false.
    private boolean isPlayerOnAnExit;
    //True if player is in a square right next to an exit and if the exit is unlocked. False otherwise.
    private boolean isPlayerAboutToExit;
    //True when player has completed game.
    private boolean isGameOver;

    public static void main(String[] args) {
        new Game().play();
    }

    public Game() {
        StdDraw.setCanvasSize(1400, 800);
        StdDraw.setXscale(-0.5, 11.5);
        StdDraw.setYscale(-0.5, 7.5);
        StdDraw.enableDoubleBuffering();
        Room attic = new Room("Attic");
        //Text which pops up when player is at the attic exit.
        String[] atticExitText = new String[2];
        //Text which pops up when exit is locked.
        atticExitText[0] = "There’s a door in the floor, but it’s incredibly small - /maybe a chihuahua could fit through it, but you definitely couldn’t./" +
                "You try to open it to see what it leads to, but you can’t get it open.";
        //Text which pops up when isPlayerAboutToExit is true.
        atticExitText[1] = "Having seen Alice in Wonderland enough times as a kid to know/how these things work, you figure you might as well drink whatever’s/" +
                "in the bottle and see if it makes you small enough to get through/the door. You pull out the stopper and take a few sips./It tastes vile, and you " +
                "feel like you might puke./There’s a strange trembling in your body, and you look down at yourself to see/that you are indeed shrinking. Your body" +
                " contracts in on itself/until you are small enough to open the tiny door. You open it,/and drop down into a passageway, which leads you to a different room…///" +
                "(Press 'e' while on the yellow square to exit.)";
        attic.getExit().setText(atticExitText);
        //Text prompts for when player is at bureau.
        String[] bureauTextArray = new String[6];
        //what you see when you first approach the bureau
        bureauTextArray[0] = "A dirty sheet is draped over something that seems to be a bureau./The music feels louder over here. You yank the sheet down,/" +
                "and clouds of dust fill the already dusty and stale air./It is indeed a bureau - it seems incredibly old. An ominous energy emanates/from it. " +
                "You’re afraid to open it, but the music draws you in./You pull open the door. On the back wall of the bureau/is a mirror - but strangely, you don’t " +
                "see your own reflection in it./Sitting on a shelf is a music box, with a little dancer figurine/slowly spinning. It doesn’t seem to be slowing down " +
                "or stopping -/ don’t music boxes usually shut off at some point?///" +
                "To close the bureau and go explore the rest of the room, press 1.//" +
                "To pull down the mirror to see if there’s any secret compartment behind it, press 2.//" +
                "To twist the dancer in the opposite direction that it’s spinning,/hoping it will stop this creepy, incessant music, press 3.";
        //The text that pops up depending on which option the user chooses.
        bureauTextArray[1] = "You close the bureau, and proceed to examine the rest of the room.";
        bureauTextArray[2] = "As you attempt to pull the mirror from the wall of the bureau,/it suddenly just shatters. The dancing figurine begins to spin faster and/" +
                "faster, the music becoming a shrieking pitch, which pierces your ears/and seems to rattle your brain. You pass out.";
        bureauTextArray[3] = "You grab the dancer and twist it in the opposite direction./A secret door on the music box pops open: inside is a pocketknife and/" +
                "a matchbox, with no matches in it. You put the pocketknife in your pocket, and/decide to grab the matchbox as well, in case you need it later—perhaps/" +
                "you can find a match somewhere. You close the bureau, and move on/to explore the rest of the room. Thankfully, the music has stopped.";
        //index 4 in the array is usually where the text would be for when you approach a POI before completing previous POI's.
        // In the case of POI1, this is irrelevant, so the text is ""
        bureauTextArray[4] = "";
        //This is the intro text that is visible until the player successfully completes the bureau challenge.
        bureauTextArray[5] = "Where am I? Why do I feel so dizzy? ///You have awoken in a room you don’t recognize. It’s dim, and cramped,/with no windows. There’s some light from a lamp in the corner, but not" +
                " much./Maybe it’s an attic? You feel claustrophobic, confused, and very uneasy./Last you remember, you had been out with friends, just having a casual Friday" +
                " night./What is this place? You check your pockets for your cell phone, but it’s not there./A muffled, tinkling music is playing from somewhere in the room -" +
                "/it’s a familiar tune, but you can’t quite place it...///Your task is to escape! Use ASDW keys to navigate./When you reach an exit, press 'e' to open it. ";
        int bureauCorrectAnswer = 3;
        int bureauOrder = 0;
        PointOfInterest bureau = new PointOfInterest("bureau", bureauTextArray, bureauCorrectAnswer, bureauOrder, 1, 7);
        //Adds bureau to attic's point of interest list.
        attic.addPOI(bureau);
        //Text array for when player is at the bed.
        String[] bedTextArray = new String[5];
        //what you see when you approach the bed, if you have already completed bureau.
        bedTextArray[0] = "A twin-sized bed sits in the corner. Does someone live here?/Why would anyone want to live in such a creepy, windowless room?/" +
                "/You’re already feeling pretty tired, even though you just woke up./To lay down for a nap on the bed so you can get more/energy and then figure" +
                " out how to get out of here, press 1.//" +
                "To look under the mattress, press 2.//." +
                "To cut open the pillow to see if anything is hidden inside, press 3./";
        //The text that pops up depending on which option the user chooses.
        bedTextArray[1] = "Though the bed seems pretty creepy, you really are tired./You lay down on the bed and close your eyes for a nap…./" +
                "Who knows how much time has passed when you wake up./You blink your eyes open, afraid of what you might see. Nothing/" +
                "seems too different. You sit up and then you realize that next/to the pillow, on the bed, is a match. Was that there/ " +
                "before? You’re pretty sure you would’ve noticed it if/it had been there already. But who put it there? You quickly/" +
                "glance around the room again, but no one is there./Your sense of unease increases significantly—but that match could be/" +
                "pretty helpful. You pull the matchbox out of your pocket and/put the match in there. You shiver a little as you get out of the bed.";
        bedTextArray[2] = "You lift up the mattress to see if you might find anything underneath./You notice what looks like a scrap of paper and" +
                " pick/it up. It’s a photograph - there’s a child on a swing, smiling, and a fence./In the background there’s another figure, back " +
                "turned to the camera,/seemingly looking at something on the other side of the fence./The figure is slightly blurry, and you can’t make " +
                "out what’s/behind the fence. There’s something compelling about it, so you put it in your pocket,/and then go look at what else is in the room.";
        bedTextArray[3] = "Given that you found a knife in the bureau, you figure you might/as well use it for something. Could there be something interesting/" +
                "or helpful hidden inside the pillow? Anything is possible at this point,/and you’re desperate to try whatever you can to figure out how to/" +
                "get out of this place. You pull out the pocketknife and drive the/blade into the pillow. Immediately, you hear a horrific scream. The little/ " +
                "light that was coming from the lamp shuts off, and you are/immersed in total darkness. You feel cold all of the sudden, and then it feels/" +
                "like you are suffocating, as if someone is strangling you./You put your hands out in front of you to push away whoever/is assaulting you, " +
                "but no one is there./You continue to choke, until eventually you pass out.";
        //The text the user sees if they approach the bed before completing the bureau challenge.
        bedTextArray[4] = "A twin-sized bed sits in the corner. Does someone live here?/Why would anyone want to live in such a creepy, windowless room?";
        int bedCorrectAnswer = 1;
        int bedOrder = 1;
        PointOfInterest bed = new PointOfInterest("bed", bedTextArray, bedCorrectAnswer, bedOrder, 5, 3);
        //Adding bed to the points of interest list
        attic.addPOI(bed);
        //text for desk
        String[] deskTextArray = new String[5];
        //when you approach desk, if you've already completed bureau and bed
        deskTextArray[0] = "There’s a desk, with nothing much on it other than a pen,/a piece of paper with writing on it, and a candle. There are/a few " +
                "drawers as well. You look at the piece of paper. It says,/in messy handwriting, “If you’re reading this, I’m sorry. They told/me not to " +
                "play with the Ouija board, but I didn’t/ think something like this could actually happen. I always thought it/ was just a game… but there’s" +
                " no way out now./I’m trapped. You’re trapped. I’m so sorry…”///For some reason, this letter fills you with rage. Who wrote this, and why/did " +
                "they mess with the Ouija board? Why are/they saying there’s no hope in trying to get out of here? The last thing/you want to do is admit that " +
                "you’re trapped in this creepy place,/and you hate whoever wrote that letter for insisting that you are.///Press 1 to grab the candle, in case you " +
                "need it later, and then move on.//Press 2 to use your only match to burn this stupid letter.//Press 3 to try to open the desk drawers.";
        //Text for different options selected
        deskTextArray[1] = "You put the candle in your pocket (you have fairly large pockets),/but within seconds, it spontaneously ignites and sets you on fire./" +
                "This house or wherever you are is clearly cursed,/and your decision angered the spirits.";
        deskTextArray[2] ="You strike the match and bring the flame to the top corner of the letter./However, as the paper becomes engulfed in flame, rather than/" +
                "disintegrating, you watch as a coating of oil and ink seems to melt off/the paper, erasing the original message and revealing text beneath:/" +
                "“Top drawer” is all it says, in glowing red ink. All three of the desk/drawers slowly slide open. Each drawer contains a small glass bottle,/" +
                "each dusty and filled with dark liquid. Do you trust the letter’s advice?/What choice do you have? You grab the bottle from the top drawer.";
        deskTextArray[3] = "You try to open the desk drawers, but they're all locked.";
        //Text for if you approach bed before completing other challenges
        deskTextArray[4] = "There’s a desk, with nothing much on it other than a pen,/a piece of paper with writing on it, and a candle.";
        int deskCorrectAnswer = 2;
        int deskOrder = 2;
        PointOfInterest desk = new PointOfInterest("desk", deskTextArray, deskCorrectAnswer, deskOrder, 0, 5);
        //Adding bed to POI list
        attic.addPOI(desk);
        //Initializing next room: codebook
        Room codebook = new Room("codebook");
        String[] codebookExitText = new String[2];
        codebookExitText[0] = "The door is locked.";
        codebookExitText[1] = "The door has popped open, to reveal the outside, at last!/You never thought you'd see light again, but now/" +
                "all you have to do is move through that door to be free..../ Unless the yard is cursed too?";
        codebook.getExit().setText(codebookExitText);
        String[] codebookTextArray = new String[5];
//what you see when you first approach the codebook
        codebookTextArray[0] = "There's a small mirror on a table. As you watch, words appear in the mirror:///" +
                "You will see a message and it contains a secret code. Crack the code to move on./" +
                "Good luck!/" +
                "if you Take too long to fIgure this out then you are dooMed./ ThEy wIll Search and find yoU. Pressure is on!/" +
                "Press the number of the answer you believe is correct:/ 1. Time is out/2. Times up/3. Times gone";
        codebookTextArray[1] = "The mirror shatters, and shards of glass fly into your face./ Then, magically, they rearrange themselves into a new" +
                " mirror.";
        codebookTextArray[2] = "The surface of the mirror glows blue - you suppose that means you got it right.";
        codebookTextArray[3] = "The mirror becomes so hot it burns your hands. You drop it and it shatters./Then, magically, the pieces rearrange themselves into" +
                " a new mirror.";
        codebookTextArray[4] = "";
        int codebookCorrectAnswer = 2;
        int codebookOrder = 0;
        PointOfInterest codebook1 = new PointOfInterest("codebook", codebookTextArray, codebookCorrectAnswer, codebookOrder, 1, 7);
        codebook.addPOI(codebook1);
        String[] decipherTextArray = new String[5];
//what you see when you first approach the codebook
//the sentence with the code in it
        decipherTextArray[0] = "There is a large book, which opens as you approach it./ Inside, the text reads://" +
                "Crack this code to get a secret potion that will momentarily make you look like a ghost./ This will " +
                "give you enough time to escape this room if you move fast!/" + "Hint: Know your alphabet order!/ "+
                "            /"+
                "            /" +
                "            __     __     __     __     __/"+
                "            /           7      8     15      19    20/"+
                "            /" +
                "Press the number of the answer you believe is correct:/ 1. scary/2. ghost/3. witch";
//the answer options. 2 is correct
        decipherTextArray[1] = "Nothing happens. You must not have cracked the code.";
        decipherTextArray[2] = "A secret compartment in the book pops open, and inside is/a bottle containing a shimmery liquid. You drink it, and turn invisible.";
        decipherTextArray[3] = "Nothing happens. You must not have cracked the code.";
        decipherTextArray[4] = "A large book sits on a table. You try to open it, but it's sealed shut.";
        int decipherCorrectAnswer = 2;
        int decipherOrder = 1;
        PointOfInterest decipher = new PointOfInterest("decipher", decipherTextArray, decipherCorrectAnswer, decipherOrder, 3, 4);
        codebook.addPOI(decipher);

        Room garden = new Room("Garden");
        String[] gardenExitText = new String[2];
        gardenExitText[0] = "You're in a garden, surrounded by an insurmountable stone wall./You try to open the high, foreboding gate," +
                " but it won't budge.";
        gardenExitText[1] = "With the key you got from the gravestone, you can now open the gate./Congratulations - you are free!";
        garden.getExit().setText(gardenExitText);
        String[] valerianTextArray = new String[5];
        valerianTextArray[0] = "The wilds of valerian plants are tangled together and spiraling over a rusty swing./ " +
                "You remember your mom making you valerian root tea./" +
                "There seems to be something lying among the plants.. Read carefully:/ " +
                "I have cities, but no people, I have mountains, but no trees, I have rivers, but no fish.///" +
                "Press 1 to not touch anything.../" +
                "Valerian root is used in dark magic rituals, it's known to be toxic./" +
                "Press 2 to clean the map from the soil and take it with you./" +
                "Press 3 to try to use the swing./";
        valerianTextArray[1] = "You move away from the valerian and explore the rest of the yard.";
        valerianTextArray[2] = "Grabbing the map, you know you've made the right choice./You put it in your pocket and keep exploring.";
        valerianTextArray[3] = "You get on the swing, and it immediately starts swinging faster and faster./You try to get off, but you feel stuck." +
                "The swing continues swinging harder and harder,/until you pass out from nausea and sheer terror.";
        valerianTextArray[4] = "";
        int valerianCorrectAnswer = 2;
        int valerianOrder = 0;
        PointOfInterest valerian = new PointOfInterest("valerian plant", valerianTextArray, valerianCorrectAnswer, valerianOrder, 1, 3);
        garden.addPOI(valerian);
        String[] gravestoneTextArray = new String[5];
        gravestoneTextArray[0] = "Keep the map after escaping the rooms./" + "It will guide you back home in case you forget where home is./" +
                "Remember someone you lost recently;/ their spirit remains and they're helping you escape the last room./" + "In order for that to happen/" +
                "you have to clean their resting area from stones and weed so they can be liberated./" + "Something is written on one of the stones..Read carefully:/ " +
                "Mouth-less, but I'll tell you a name that's not mine./ I'll show you the years I've seen but I have no eyes. Memories I have, but I have no mind.///" +
                "Press 1 to break the stone surrounding the gravestone./" + "There is a light underneath it.. it might be the key./" +
                "Press 2 to sing a prayer with hope that the spirit shows itself./" +
                "Press 3 to walk away because this is frightening!!";
        gravestoneTextArray[1] = "You kick at the stone until it breaks into pieces, and see/that there is a glimmering key underneath. You pick/" +
                "up the key and head for the garden gate.";
        gravestoneTextArray[2] = "You sing a prayer to call the spirit. The sky turns dark/and suddenly it is freezing. The wind starts howling,/" +
                "and you realize you manifested something truly evil./You hear a shrieking, feel pain on every inch of your body, and then faint.";
        gravestoneTextArray[3] = "You walk away from the gravestone, afraid to anger any spirits.";
        gravestoneTextArray[4] = "There is a gravestone, surrounded by rocks and fresh flowers.";
        int gravestoneCorrectAnswer = 1;
        int gravestoneOrder = 1;
        PointOfInterest gravestone = new PointOfInterest("mom's gravestone", gravestoneTextArray, gravestoneCorrectAnswer, gravestoneOrder, 3,3);
        garden.addPOI(gravestone);
        //Setting the codebook room to the attic exit
        attic.setExit(codebook);
        //Setting the garden to the codebook exit
        codebook.setExit(garden);
        //You start in the attic
        currentRoom = attic;
        this.points = currentRoom.getPoints();
        this.player = currentRoom.getPlayer();
        this.isPlayerOnAPoint = false;
        //whatPoint is -1 while the player is not on a point.
        this.whatPoint = -1;
        this.selection = 0;

    }
    /** Play method for running the whole thing. */
    private void play() {
        while (true) {
            StdDraw.pause(75);
            draw();
            handleInput();
            update();
            draw();
            update();
            player = currentRoom.getPlayer();
            points = currentRoom.getPoints();


        }
    }


    /**
     * Handles all user input
     * It is used for moving the player, interacting with points of interest and using the exits of the room
     */
    private void handleInput() {
        //AWDS keys are used to move player.
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            if (player.getY() < 7) {
                player.move(0, 1);
            }
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            if (player.getX() > 0) {
                player.move(-1, 0);
            }
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            if (player.getX() < 5)
                player.move(1, 0);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            if (player.getY() > 0) {
                player.move(0, -1);
            }
            //pressing 'e' while at an exit will unlock it if you've completed all challenges
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            currentRoom = currentRoom.nextRoom();
            selection = 0;
            update();
            //reads player input if they're at a point of interest to set the selection variable
        } else if (isPlayerOnAPoint) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
                points.get(whatPoint).setSelected(1);
                selection = 1;
                if (selection == points.get(whatPoint).getCorrectAnswer()) {
                    points.get(whatPoint).setCompleted(true);
                    if ((whatPoint + 1) < points.size()) {
                        //Sets for the next point of interest that the previous one has been completed, so the text prompts will be revealed
                        //when the player approaches.
                        points.get(whatPoint + 1).setPreviousCompleted(true);
                    }
                }

            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                points.get(whatPoint).setSelected(2);
                selection = 2;
                if (selection == points.get(whatPoint).getCorrectAnswer()) {
                    points.get(whatPoint).setCompleted(true);
                    if ((whatPoint + 1) < points.size()) {
                        points.get(whatPoint + 1).setPreviousCompleted(true);
                    }
                }

            } else if (StdDraw.isKeyPressed(KeyEvent.VK_3)) {
                points.get(whatPoint).setSelected(3);
                selection = 3;
                if (selection == points.get(whatPoint).getCorrectAnswer()) {
                    points.get(whatPoint).setCompleted(true);
                    if ((whatPoint + 1) < points.size()) {
                        points.get(whatPoint + 1).setPreviousCompleted(true);
                    }
                }

            }


        }

    }


    /**
     * Draws the board, points of interests, exits, player, and all the text
     */
    private void draw() {
        StdDraw.clear();
        drawBoard();
        drawIntro();
        //If player is on a point of interest, draws text for that point of interest
        if (!(whatPoint == -1)) {
            drawText();
        }
        //If player is on an exit, prints the text
        if(isPlayerOnAnExit){
            drawExitText();
        }
        //If player is about to exit, prints the text
        if(isPlayerAboutToExit){
            drawAboutToExitText();
        }
        //If game is over, draws a "you win" screen
        if(isGameOver){
            drawGameOver();
        }
        StdDraw.show();

    }

    /** While player is in the attic and has not completed bureau, and also isn't standing on a point of interest or the exit, prints intro text */
    private void drawIntro(){
        if(currentRoom.getName().equals("Attic") && !(points.get(0).isCompleted()) && !isPlayerOnAPoint && !isPlayerOnAnExit){
            if (points.get(0).getTextArray()[5].length() > 84) {
                String[] strings = points.get(0).getTextArray()[5].split("/");
                for (int i = 0; i < strings.length; i++) {
                    StdDraw.text(8.5, 5 - .2 * i, strings[i]);
                }
            }
        }
    }

    /** Draws board */
    private void drawBoard() {
        StdDraw.setPenColor(Color.BLACK);
        for (double i = 0; i < 6; i += 1) {
            for (double j = 0; j < 10; j += 1) {
                StdDraw.square(i, j, 0.5);
            }
        }
        StdDraw.setPenColor(Color.GREEN);
        for (PointOfInterest p : points) {
            StdDraw.filledSquare(p.getX(), p.getY(), 0.3);
        }
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledSquare(currentRoom.getExit().getX(), currentRoom.getExit().getY(), 0.3);
        int x = player.getX();
        int y = player.getY();
        StdDraw.setPenColor(Color.red);
        StdDraw.filledCircle(x, y, 0.3);
        StdDraw.setPenColor(Color.black);


    }
    /** Draws text if player is at point of interest */
    private void drawText() {
        PointOfInterest p = points.get(whatPoint);
        if (p.getSelected() != 0) {
            String text = p.getTextArray()[p.getSelected()];
            if (text.length() > 84) {
                String[] strings = text.split("/");
                for (int i = 0; i < strings.length; i++) {
                    StdDraw.text(8.5, 5 - .2 * i, strings[i]);
                }

            } else {
                StdDraw.text(8.5, 4, text);

            }

        } else if (p.isPreviousCompleted() && !p.isCompleted()) {
            if (p.getTextArray()[0].length() > 84) {
                String[] strings = p.getTextArray()[0].split("/");
                for (int i = 0; i < strings.length; i++) {
                    StdDraw.text(8.5, 5 - .2 * i, strings[i]);
                }

            } else {
                StdDraw.text(8.5, 4, p.getTextArray()[0]);

            }

        } else if (p.getTextArray()[4].length() > 84) {
            String[] strings = p.getTextArray()[4].split("/");
            for (int i = 0; i < strings.length; i++) {
                StdDraw.text(8.5, 5 - .2 * i, strings[i]);
            }

        } else {
            StdDraw.text(9, 4, p.getTextArray()[4]);


        }

    }
    /** Draws text if player is at exit and it's locked still. */
    public void drawExitText(){
        if (currentRoom.getExit().isLocked()){
            if (currentRoom.getExit().getText()[0].length() > 84) {
                String[] strings = currentRoom.getExit().getText()[0].split("/");
                for (int i = 0; i < strings.length; i++) {
                    StdDraw.text(8.5, 5 - .2 * i, strings[i]);
                }

            } else {
                StdDraw.text(8.5, 4, currentRoom.getExit().getText()[0]);

            }
        }


    }

    /** Draws text for when the exit is unlocked and the player is about to leave the room. */
    public void drawAboutToExitText(){
        if (currentRoom.getExit().getText()[1].length() > 84) {
            String[] strings = currentRoom.getExit().getText()[1].split("/");
            for (int i = 0; i < strings.length; i++) {
                StdDraw.text(8.5, 5 - .2 * i, strings[i]);
            }

        } else {
            StdDraw.text(8.5, 4, currentRoom.getExit().getText()[1]);

        }
        StdDraw.pause(50);

    }
    /** Draws you win screen, when game is completed */
    public void drawGameOver(){
        StdDraw.clear();
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(2.0);
        StdDraw.text(5, 4, "YOU WIN!");
    }

    /** Checks if player is on a point of interest and sets boolean. */
    public void isPlayerOnAPoint() {
        for (PointOfInterest p : points) {
            if (p.getX() == player.getX() && p.getY() == player.getY()) {
                isPlayerOnAPoint = true;
                return;
            }
            //Makes sure selected is reset to 0 when player is not at a point of interest.
            else{
                p.setSelected(0);
            }

        }
        isPlayerOnAPoint = false;
    }

    /** Checks if player is at an exit and sets boolean. */
    public void isPlayerOnAnExit() {
        if (currentRoom.getExit().getX() == player.getX() && currentRoom.getExit().getY() == player.getY()) {
                isPlayerOnAnExit = true;
                return;
            }

        isPlayerOnAnExit = false;
    }
    /** Checks if player is about to exit and sets boolean.*/
    public void isPlayerAboutToExit() {
        if (((player.getX() == 4 && player.getY() == 7) || (player.getX()==5 && player.getY()==6)) && !(currentRoom.getExit().isLocked())) {
            isPlayerAboutToExit = true;
            return;
        }

        isPlayerAboutToExit = false;
    }

    /**
     * Updates all booleans based on player input. Checks if door should be locked.
     */
    public void update() {
        isPlayerOnAPoint();
        whatPoint();
        isPlayerOnAnExit();
        isPlayerAboutToExit();
        isGameOver();
        currentRoom.shouldDoorBeLocked();

    }


    /**Checks what point of interest player is on. Sets whatPoint variable to the order of the point of interest
     * or -1 if player is not on a point of interest. */
    public void whatPoint() {
        for (PointOfInterest p : points) {
            if (p.getX() == player.getX() && p.getY() == player.getY()) {
                whatPoint = p.getOrder();
                return;

            }

        }
        whatPoint = -1;

    }

    /** Checks if player has won game and is exiting final stage. */
    public void isGameOver(){
        if(currentRoom.getName().equals("Garden") && (player.getX() == 5 && player.getY()==7) && !(currentRoom.getExit().isLocked())){
            isGameOver = true;
            return;
        }
        isGameOver = false;
    }

}

