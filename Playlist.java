/**
 * Pablo Garces, 06/18/2023,
 * This class represents a music Playlist
 */
public class Playlist {
    //Declare all the variables.
    private Song head, tail; //Dummy nodes head and tail.
    private int size, totalDuration; //Playlist size and total duration.
    
    /**
     * No-arg constructor that explicitly initializes all instance fields.
     */
    public Playlist (){
        head = new Song();//Set dummy nodes.
        tail = new Song();
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
        totalDuration = 0;
    }

    /**
     * Get the number of songs in the playlist.
     */
    public int getSize(){
        return size;
    }

    /**
     * Get the total duration of the playlist.
     * @return
     */
    public int getTotalDuration(){
        return totalDuration;
    }

    /**
     * Add a song to the playlist.
     * @param song
     */
    public void addSong(Song song){
        //Add a new song.
        Song lastSong = tail.getPrevious();
        lastSong.setNext(song);
        song.setPrevious(lastSong);
        song.setNext(tail);
        tail.setPrevious(song);
        //Increment size and duration.
        size++;
        totalDuration += song.getLength();
    }

    /**
     * Insert a song after the current one.
     * @param newSong
     * @param currentSong
     */
    public void insertAfterCurrent(Song newSong, Song currentSong) {
        //Add a song after the current one.
        Song nextSong = currentSong.getNext();
        currentSong.setNext(newSong);
        newSong.setPrevious(currentSong);
        newSong.setNext(nextSong);
        nextSong.setPrevious(newSong);
        //Increment size and duration.
        size++;
        totalDuration += newSong.getLength();
    }

    /**
     * Get the head dummy node.
     * @return head
     */
    public Song getHead() {
        return head;
    }

    /**
     * Get the tail dummy node.
     * @return tail
     */
    public Song getTail() {
        return tail;
    }

    /**
     * Remove a song from the playlist.
     * @param song
     */
    public void removeSong(Song song){
        //Remove the song.
        Song previous = song.getPrevious();
        Song next = song.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        //Increment size and duration.
        size--;
        totalDuration -= song.getLength();
    }

    /**
     * Print the playlist information.
     */
    @Override
    public String toString() {
        //Create a string builder to append everything.
        StringBuilder playlistStr = new StringBuilder();

        //Set the current song.
        Song current = head.getNext();

        //Append the song names until the last song.
        while (current != tail) {
            playlistStr.append(current.getName()).append("\n");
            current = current.getNext();
        }

        //Convertion into minutes and seconds.
        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;

        //Append the information.
        playlistStr.append("Number of songs: ").append(size).append("\n");
        playlistStr.append("Playlist duration: ").append(minutes).append(" minutes, ").append(seconds).append(" seconds");

        return playlistStr.toString();
    }


}
