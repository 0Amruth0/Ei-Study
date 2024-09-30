interface mediaPlayer {
    void play();
}

class MP3Player implements mediaPlayer {
    @Override
    public void play(){
        System.out.println("Playing MP3... ... ...");
    }
}

class MP4Player {
    public void playMP4() {
        System.out.println("Playing MP4 .... ....");
    }
}

class mediaAdapter implements mediaPlayer {
    private MP4Player mp4player;

    public mediaAdapter(MP4Player mp4player){
        this.mp4player = mp4player;
    }

    @Override
    public void play() {
        mp4player.playMP4();
    }
}

public class Main {
    public static void main(String[] args) {
        mediaPlayer player = new mediaAdapter(new MP4Player());
        player.play();

        mediaPlayer player2 = new MP3Player();
        player2.play();
    }
}