/**
 * This class represents a music Playlist.
 */
public class Playlist {
    private Song head, tail, currentSong;
    private int size, totalDuration;
    
    /**
     * Constructor that accpets arguments.
     */
    public Playlist (){
        head = new Song();
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

    public int getTotalDuration(){
        return totalDuration;
    }

    public void addSong(Song song){
        Song lastSong = tail.getPrevious();
        lastSong.setNext(song);
        song.setPrevious(lastSong);
        song.setNext(tail);
        tail.setPrevious(song);
        size++;
        totalDuration += song.getLength();
    }

    public void insertAfterCurrent(Song newSong, Song currentSong) {
        if (currentSong == null) {
            return;  // Invalid current song, do nothing
        }

        Song nextSong = currentSong.getNext();
        currentSong.setNext(newSong);
        newSong.setPrevious(currentSong);
        newSong.setNext(nextSong);
        nextSong.setPrevious(newSong);
        size++;
        totalDuration += newSong.getLength();
    }

    public Song getHead() {
        return head;
    }

    public Song getTail() {
        return tail;
    }

    public void removeSong(Song song){
        if (size == 0){
            return;
        }

        Song previous = song.getPrevious();
        Song next = song.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        size--;
        totalDuration -= song.getLength();
    }

    @Override
    public String toString() {
        StringBuilder playlistStr = new StringBuilder();

        Song current = head.getNext();
        int songCount = 0;
        int playlistDuration = 0;

        while (current != tail) {
            playlistStr.append(current.getName()).append("\n");
            songCount++;
            playlistDuration += current.getLength();
            current = current.getNext();
        }

        int minutes = playlistDuration / 60;
        int seconds = playlistDuration % 60;

        playlistStr.append("Number of songs: ").append(songCount).append("\n");
        playlistStr.append("Playlist duration: ").append(minutes).append(" minutes, ").append(seconds).append(" seconds");

        return playlistStr.toString();
    }


}
