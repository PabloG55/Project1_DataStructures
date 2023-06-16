/**
 * Pablo Garces, 06/18/2023,
 * This class represent an implementation of the Playlist and Song classes.
 */
import java.util.Scanner;

public class Demo { 
    //Create a current song object.
    public static Song currentSong; 
    public static void main (String[]args){
      //Declare input variable and initialize current song variable.
      int input; 

      //Declare the constants.
      final int MIN_INPUT = 1;
      final int MAX_INPUT = 8;

      //Create a Scanner object used to read input from the console.
      Scanner scnr = new Scanner(System.in);

      //Create an instance of the Playlist class.
      Playlist playlist = new Playlist();
  
      //Initialize the do while loop to mantain the menu working.
      do {
        //Print menu and read the input.
        printMenu();
        input = scnr.nextInt();

        //Validate the input.
        while (input < MIN_INPUT || input > MAX_INPUT){
          System.out.print("Please choose a number from 1 to 8: ");
          input = scnr.nextInt();
          }
        
        //Create all the menu options.
        switch (input){
          case 1:
            System.out.println();
            addSongToPlaylist(scnr, playlist);
            System.out.println();
            break;
          case 2:
            System.out.println();
            addAfterCurr(scnr, playlist);
            System.out.println();
            break;
          case 3:
            System.out.println();
            printPlaylist(playlist);
            System.out.println();
            break;          
          case 4:
            System.out.println();
            getCurrSong(playlist);
            System.out.println();
            break;
          case 5:
            System.out.println();
            removeCurrSong(playlist);
            System.out.println();
            break;
          case 6:
            System.out.println();
            skipToNext(playlist);
            System.out.println();
            break;
          case 7:
            System.out.println();
            returnToPrevious(playlist);
            System.out.println();
            break;
          case 8: //End the loop.
            System.out.println();
            System.out.println("Exiting...");
            System.out.println();
            break;
          default:
            break;
        }
      } while (input != MAX_INPUT);
    }

  /**
   * Print menu.
   */
  public static void printMenu (){
      System.out.println("What would you like to do?");
      System.out.println(" 1. Add a Song to the Playlist.");
      System.out.println(" 2. Insert a new Song after the Song that is currently playing.");
      System.out.println(" 3. Print the contents of the Playlist.");
      System.out.println(" 4. Display the current Song.");
      System.out.println(" 5. Remove the current song.");
      System.out.println(" 6. Skip to the next song.");
      System.out.println(" 7. Return to the previous song.");
      System.out.println(" 8. Exit.");
      System.out.print("Chose an option from the list: ");
    }

  /**
   * Add a song to the playlist.
   * @param scnr
   * @param playlist
   */
  public static void addSongToPlaylist (Scanner scnr, Playlist playlist) {
      //Read song details.
      scnr.nextLine(); 
      System.out.print("Enter the song name: ");
      String name = scnr.nextLine();
      System.out.print("Enter the artist: ");
      String artist = scnr.nextLine();
      System.out.print("Enter the album: ");
      String album = scnr.nextLine();
      System.out.print("Enter the length (in seconds): ");
      int length = scnr.nextInt();

      //Create a new Song object
      Song song = new Song(name, artist, album, length);

      //Add the song to the playlist
      playlist.addSong(song);
      
      //If there is any current song put it as current.
      if (currentSong == null) {
        currentSong = song;
    }

    System.out.println("\n" + song.getName() +" added to the playlist.");    
  }

  /**
   * Add a song after the current one.
   * @param scnr
   * @param playlist
   */
  public static void addAfterCurr (Scanner scnr, Playlist playlist){
    //If there is no current song just add the song to the plalist.
    if (currentSong == null) {
      addSongToPlaylist(scnr, playlist);
    } else { //If there is a current add it after the current.
      //Read song details.
      scnr.nextLine(); 
      System.out.print("Enter the song name: ");
      String name = scnr.nextLine();
      System.out.print("Enter the artist: ");
      String artist = scnr.nextLine();
      System.out.print("Enter the album: ");
      String album = scnr.nextLine();
      System.out.print("Enter the length (in seconds): ");
      int length = scnr.nextInt();

      //Create a new Song object
      Song song = new Song(name, artist, album, length);

      playlist.insertAfterCurrent(song, currentSong);

      System.out.println("\n" + song.getName() +" added to the playlist.");
       
      }
  }

  /**
   * Print the current song.
   * @param playlist
   */
  public static void getCurrSong (Playlist playlist) {
    //If there is no current song, print an Error.
    if (currentSong == null) {
      System.out.println("There is no currently playing song, add one first.");
    } else { //If there is a current song, print details.
      System.out.println("Currently playing song:");
      System.out.println("Name: " + currentSong.getName());
      System.out.println("Artist: " + currentSong.getArtist());
      System.out.println("Album: " + currentSong.getAlbum());
      System.out.println("Length: " + currentSong.getLength() + " seconds");
      }
}

  /**
   * Remove the current song.
   * @param playlist
   */
  public static void removeCurrSong (Playlist playlist){
    //Declare song objects.
    Song tail = playlist.getTail();
    Song head = playlist.getHead();
    //If there is no current song, print an Error.
    if (currentSong == null) {
      System.out.println("There is no currently playing song, add one first.");
    }
    //If the curent song is the only one in the playlist remove it put the playlist as empty.
    else if (currentSong.getNext() == tail && currentSong.getPrevious() == head){
      playlist.removeSong(head.getNext());
      System.out.println(currentSong.getName() + " was removed, now the playlist is empty");
      currentSong = null;  
    } else { //If not just remove the song.
        playlist.removeSong(currentSong);
        //If the current song is not the last, set the current song the next one. 
        if (currentSong.getNext() != tail){
          System.out.println(currentSong.getName() + " was removed from the playlist.");
          currentSong = currentSong.getNext();
        } else { //If it is the last, set the current song the previous one.
            System.out.println(currentSong.getName() + " was removed from the playlist.");
            currentSong = currentSong.getPrevious();
          }
        
        }
  }

  /**
   * Print all the playlist.
   * @param playlist
   */
  public static void printPlaylist (Playlist playlist){
    //If there is no current song, print an Error.
    if (currentSong == null) {
      System.out.println("The playlist is empty, add a song first.");
    } else { //If there is a current song print the playlist.
      System.out.println(playlist);
    }
  }

  /**
   * Skip to the the next song.
   * @param playlist
   */
  public static void skipToNext (Playlist playlist){
    //If there is no current song, print an Error.
    if (currentSong == null) {
        System.out.println("The playlist is empty, add a song first.");
      }
      //If it is the last song in the playlist, print an Error. 
      else if (currentSong.getNext() == playlist.getTail()){
        System.out.println("The playlist doesn't have a next song. ");
      } else { //If there is a next song in the playlisyt skip it to the next one.
          currentSong = currentSong.getNext();
          System.out.println("Skiped to " + currentSong.getName());
        }
  }

  /**
   * Return to the previous song.
   * @param playlist
   */
  public static void returnToPrevious (Playlist playlist){
    //If there is no current song, print an Error.
    if (currentSong == null) {
      System.out.println("The playlist is empty, add a song first.");
    }
    //If there is not a previous song in the playlist, print an error.
    else if (currentSong.getPrevious() == playlist.getHead()){
      System.out.println("The playlist doesn't have a previous song. ");
    } else { //If there is a previous song in the playlist return to it.
        currentSong = currentSong.getPrevious();
        System.out.println("Returned to " + currentSong.getName());
      }
  }
}
