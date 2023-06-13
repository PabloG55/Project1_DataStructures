import java.util.Scanner;

public class Demo {
    public static Song currentSong;
    public static void main (String[]args){
      int input;

      Song currentSong = null;
      //Create a Scanner object used to read input from the console
      Scanner scnr = new Scanner(System.in);

      //Create an instance of the Playlist class
      Playlist playlist = new Playlist();
     
      do {
        printMenu();
        input = scnr.nextInt();
        while (input < 1 || input > 8){
          System.out.print("Please choose a number from 1 to 8: ");
          input = scnr.nextInt();
          }
        
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
          case 8:
            System.out.println();
            System.out.println("Exiting...");
            System.out.println();
          default:
            break;
        }
      } while (input != 8);
    }

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

  public static void addSongToPlaylist (Scanner scnr, Playlist playlist) {
      // Read song details from the user
      scnr.nextLine(); 
      System.out.print("Enter the song name: ");
      String name = scnr.nextLine();
      System.out.print("Enter the artist: ");
      String artist = scnr.nextLine();
      System.out.print("Enter the album: ");
      String album = scnr.nextLine();
      System.out.print("Enter the length (in seconds): ");
      int length = scnr.nextInt();

      // Create a new Song object
      Song song = new Song(name, artist, album, length);

      // Add the song to the playlist
      playlist.addSong(song);
      
      if (currentSong == null) {
        currentSong = song;
    }

    System.out.println("\nSong added to the playlist.");    
  }

  public static void addAfterCurr (Scanner scnr, Playlist playlist){
    if (currentSong == null) {
      addSongToPlaylist(scnr, playlist);
    } else {
      // Read song details from the user
      scnr.nextLine(); 
      System.out.print("Enter the song name: ");
      String name = scnr.nextLine();
      System.out.print("Enter the artist: ");
      String artist = scnr.nextLine();
      System.out.print("Enter the album: ");
      String album = scnr.nextLine();
      System.out.print("Enter the length (in seconds): ");
      int length = scnr.nextInt();

      // Create a new Song object
      Song song = new Song(name, artist, album, length);

      playlist.insertAfterCurrent(song, currentSong);

      System.out.println("\nSong inserted into the playlist.");
      }
  }

  public static void getCurrSong (Playlist playlist) {
    if (currentSong == null) {
      System.out.println("There is no currently playing song, add one first.");
    } else {
      System.out.println("Currently playing song:");
      System.out.println("Name: " + currentSong.getName());
      System.out.println("Artist: " + currentSong.getArtist());
      System.out.println("Album: " + currentSong.getAlbum());
      System.out.println("Length: " + currentSong.getLength() + " seconds");
      }
}

  public static void removeCurrSong (Playlist playlist){
    if (currentSong == null) {
      System.out.println("There is no currently playing song, add one first.");
    }
    else if (currentSong.getNext() == playlist.getTail() && currentSong.getPrevious() == playlist.getHead()){
      playlist.removeSong(playlist.getHead().getNext());
      System.out.println("Current song removed, now the playlist is empty");
      currentSong = null;  
    } else {
      playlist.removeSong(currentSong);
      if (currentSong.getNext() != playlist.getTail()){
      currentSong = currentSong.getNext();
      } else {
        currentSong = currentSong.getPrevious();
      }
      System.out.println("Current song removed from the playlist.");
      }
  }

  public static void printPlaylist (Playlist playlist){
    if (currentSong == null) {
      System.out.println("The playlist is empty, add a song first.");
    } else {
      System.out.println(playlist);
    }
  }

  public static void skipToNext (Playlist playlist){
    if (currentSong == null) {
        System.out.println("The playlist is empty, add a song first.");
      } 
      else if (currentSong.getNext() == playlist.getTail()){
        System.out.println("There is any song next in the playlist");
      } else {
          currentSong = currentSong.getNext();
          System.out.println("Skiped to " + currentSong.getName());
        }
  }

  public static void returnToPrevious (Playlist playlist){
    if (currentSong == null) {
      System.out.println("The playlist is empty, add a song first.");
    } 
    else if (currentSong.getPrevious() == playlist.getHead()){
      System.out.println("There is any previous song in the playlist");
    } else {
        currentSong = currentSong.getPrevious();
        System.out.println("Returned to " + currentSong.getName());
      }
  }
}
