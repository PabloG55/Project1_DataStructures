/**
 * This class represents songs objects
 */
public class Song {
    private String name, artist, album;
    private int length;
    private Song next, previous;

    /**
     * Constructor that accpets arguments.
     * @param name
     * @param artist
     * @param album
     * @param lenght
     */
    public Song (String name, String artist, String album, int lenght){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = lenght;
        this.next = null;
        this.previous = null;
    }

    /**
     * No-arg constructor that explicitly initializes all instance fields.
     */
    public Song (){
        this.name = "none";
        this.artist = "none";
        this.album = "none";
        this.length = 0;
    }

    /**
     * Setter.
     * @param name
     */
    public void setName (String name){
        this.name = name;
    }

    /**
     * Setter.
     * @param artist
     */
    public void setArtist (String artist){
        this.artist = artist;
    }

    /**
     * Setter.
     * @param album
     */
    public void setAlbum (String album){
        this.album = album;
    }

    /**
     * Setter.
     * @param length
     */
    public void setLength (int length){
        this.length = length;
    }

    /**
     * Setter.
     * @param next
     */
    public void setNext (Song next){
        this.next = next;
    }

    /**
     * Setter.
     * @param previous
     */
    public void setPrevious (Song previous){
        this.previous = previous;
    }

    /**
     * Getter.
     * @return name
     */
    public String getName (){
        return name;
    }

    /**
     * Getter.
     * @return artist
     */
    public String getArtist (){
        return artist;
    }

    /**
     * Getter.
     * @return album
     */
    public String getAlbum (){
        return album;
    }

    /**
     * Getter.
     * @return lenght
     */
    public int getLength (){
        return length;
    }

    /**
     * Getter.
     * @return next
     */
    public Song getNext (){
        return next;
    }

    /**
     * Getter.
     * @return previous
     */
    public Song getPrevious (){
        return previous;
    }

    @Override
    public String toString() {
        return name;
    }
}
