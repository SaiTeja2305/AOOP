import java.util.Scanner;

// Adapter Pattern: Common interface for all music sources
interface MusicSource {
    void play();
}

// Concrete implementations for various music sources
class LocalFile implements MusicSource {
    @Override
    public void play() {
        System.out.println("Playing music from a local file.");
    }
}

class OnlineStreaming implements MusicSource {
    @Override
    public void play() {
        System.out.println("Playing music from an online streaming service.");
    }
}

class RadioStation implements MusicSource {
    @Override
    public void play() {
        System.out.println("Playing music from a radio station.");
    }
}

// Bridge Pattern: MusicPlayer abstraction that uses the bridge pattern
abstract class MusicPlayer {
    protected MusicSource musicSource;

    public MusicPlayer(MusicSource musicSource) {
        this.musicSource = musicSource;
    }

    public abstract void playMusic();
}

// Refined abstractions
class BasicMusicPlayer extends MusicPlayer {
    public BasicMusicPlayer(MusicSource musicSource) {
        super(musicSource);
    }

    @Override
    public void playMusic() {
        System.out.println("Basic music player:");
        musicSource.play();
    }
}

class AdvancedMusicPlayer extends MusicPlayer {
    public AdvancedMusicPlayer(MusicSource musicSource) {
        super(musicSource);
    }

    @Override
    public void playMusic() {
        System.out.println("Advanced music player:");
        musicSource.play();
    }
}

// Decorator Pattern: Base decorator class
abstract class MusicPlayerDecorator extends MusicPlayer {
    protected MusicPlayer decoratedMusicPlayer;

    public MusicPlayerDecorator(MusicPlayer musicPlayer) {
        super(musicPlayer.musicSource);
        this.decoratedMusicPlayer = musicPlayer;
    }

    @Override
    public void playMusic() {
        decoratedMusicPlayer.playMusic();
    }
}

// Concrete decorators
class Equalizer extends MusicPlayerDecorator {
    public Equalizer(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void playMusic() {
        decoratedMusicPlayer.playMusic();
        System.out.println("Equalizer settings applied.");
    }
}

class VolumeControl extends MusicPlayerDecorator {
    public VolumeControl(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void playMusic() {
        decoratedMusicPlayer.playMusic();
        System.out.println("Volume control settings applied.");
    }
}

// Main Application: Integrates all the patterns
public class MusicStreamingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Select music source
        System.out.println("Select music source:");
        System.out.println("1. Local File");
        System.out.println("2. Online Streaming");
        System.out.println("3. Radio Station");

        int sourceChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MusicSource musicSource;
        switch (sourceChoice) {
            case 1:
                musicSource = new LocalFile();
                break;
            case 2:
                musicSource = new OnlineStreaming();
                break;
            case 3:
                musicSource = new RadioStation();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Local File.");
                musicSource = new LocalFile();
                break;
        }

        // Step 2: Select music player type
        System.out.println("Select music player type:");
        System.out.println("1. Basic Music Player");
        System.out.println("2. Advanced Music Player");

        int playerChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MusicPlayer musicPlayer;
        switch (playerChoice) {
            case 1:
                musicPlayer = new BasicMusicPlayer(musicSource);
                break;
            case 2:
                musicPlayer = new AdvancedMusicPlayer(musicSource);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Basic Music Player.");
                musicPlayer = new BasicMusicPlayer(musicSource);
                break;
        }

        // Step 3: Apply decorators
        System.out.println("Would you like to add an Equalizer? (yes/no)");
        String equalizerChoice = scanner.nextLine();
        if (equalizerChoice.equalsIgnoreCase("yes")) {
            musicPlayer = new Equalizer(musicPlayer);
        }

        System.out.println("Would you like to add Volume Control? (yes/no)");
        String volumeChoice = scanner.nextLine();
        if (volumeChoice.equalsIgnoreCase("yes")) {
            musicPlayer = new VolumeControl(musicPlayer);
        }

        // Step 4: Play music
        musicPlayer.playMusic();

        scanner.close();
    }
}
