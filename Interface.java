import java.io.*;
import java.util.*;

public class Interface {
    private static BSTDictionary dictionary = new BSTDictionary();

     /*
     * Main method - serves as the entry point for the application.
     * It checks for the required input file argument, processes the input file,
     * and then enters a loop to read and process user commands until the "exit" command is issued.
     * @param args Command line arguments, expecting a single argument for the input file name.
     */
     public static void main(String[] args) {
    	// Check if an input file is provided
        if (args.length < 1) {
            System.out.println("Usage: java Interface <inputFile>");
            return;
        }

        String inputFile = args[0];
        try {
            // Read and process the input file
            readInputFile(inputFile);
        } catch (IOException | DictionaryException e) {
        	// Handle errors during file reading
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Set up for reading user commands
        StringReader keyboard = new StringReader();
        String command;
        while (true) {
        	// Read user command from keyboard
            command = keyboard.read("Enter next command: ");
            // Check for exit command
            if (command.equals("exit")) {
                System.out.println("Exiting program.");
                break;
            }
            // Process the command
            processCommand(command);
        }
     }
     
     /* Reads the input file and adds the records to the dictionary.
     * Each line of the file is expected to have a label and data,
     * with the type determined based on the data format.
     * @param inputFile The name of the file to be read.
     * @throws IOException if there's an error reading the file.
     * @throws DictionaryException if there's an error processing the dictionary.
     */
     private static void readInputFile(String inputFile) throws IOException, DictionaryException {
         BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         String label;
         while ((label = reader.readLine()) != null) {
             String line = reader.readLine();
             int type = determineType(line);
             String data = line.substring(1).trim();
             Record record = new Record(new Key(label.toLowerCase(), type), data);
             dictionary.put(record);
         }
         reader.close();
     }
     
     /* Determines the type of a record based on its line format.
     * Types are determined by specific prefixes or file extensions in the line.
     * @param line The line from the input file to determine the type of.
     * @return int representing the type of the record.
     */
     private static int determineType(String line) {
         if (line.startsWith("-")) {
             return 3; // Sound file
         } else if (line.startsWith("+")) {
             return 4; // Music file
         } else if (line.startsWith("*")) {
             return 5; // Voice file
         } else if (line.startsWith("/")) {
             return 2; // Translation
         } else if (line.endsWith(".gif")) {
             return 7; // Animated image file
         } else if (line.endsWith(".jpg")) {
             return 6; // Image file
         } else if (line.endsWith(".html")) {
             return 8; // Webpage URL
         }
         return 1; // Definition
     }
     
     
     /* Processes a user command by splitting it into parts and handling
     * it based on the command type. Supports various commands like "define", "translate",
     * "sound", "play", "say", "show", "animate", "browse", "add", "delete", "list",
     * "first", "last", and "exit".
     *
     * @param command The command string entered by the user.
     */
     private static void processCommand(String command) {
         String[] parts = command.split(" ");
         if (parts.length < 2) {
             System.out.println("Invalid command");
             return;
         }

         String w = parts[1].toLowerCase();
         try {
             switch (parts[0]) {
                 case "define":
                     handleDefine(w);
                     break;
                 case "translate":
                     handleTranslate(w);
                     break;
                 case "sound":
                     handleSound(w);
                     break;
                 case "play":
                     handlePlay(w);
                     break;
                 case "say":
                     handleSay(w);
                     break;
                 case "show":
                     handleShow(w);
                     break;
                 case "animate":
                     handleAnimate(w);
                     break;
                 case "browse":
                     handleBrowse(w);
                     break;
                 case "add":
                     int t = Integer.parseInt(parts[2]);
                     String c = combineParts(parts, 3); // Combines all parts starting from index 3
                     handleAdd(w, t, c);
                     break;
                 case "delete":
                     if (parts.length < 3) {
                         System.out.println("Invalid command format for delete");
                         break;
                     }
                     w = parts[1];
                     int k = Integer.parseInt(parts[2]);
                     handleDelete(w, k);
                     break;
                 case "list":
                     if (parts.length < 2) {
                         System.out.println("Invalid command format for list");
                         break;
                     }
                     String prefix = parts[1];
                     handleList(prefix);
                     break;
                 case "first":
                     handleFirst();
                     break;
                 case "last":
                     handleLast();
                     break;
                 case "exit":
                     handleExit();
                     break;
                 default:
                     System.out.println("Invalid command");
             }
         } catch (Exception e) {
             System.err.println("Error processing command: " + e.getMessage());
         }
     }
     
     /*
     * Retrieves and displays the definition of the specified word.
     * The word is looked up in the dictionary, and its definition is printed if found.
     * @param word The word to define.
     */
     private static void handleDefine(String word) {
         Record record = dictionary.get(new Key(word, 1));
         if (record != null) {
             System.out.println(record.getDataItem());
         } else {
             System.out.println("The word " + word + " is not in the ordered dictionary");
         }
     }
     
     /*
     * Retrieves and displays the translation of the specified word.
     * The word is looked up in the dictionary, and its translation is printed if found.
     * @param word The word to translate.
     */
     private static void handleTranslate(String word) {
         Record record = dictionary.get(new Key(word, 2));
         if (record != null) {
             System.out.println(record.getDataItem());
         } else {
             System.out.println("There is no definition for the word " + word);
         }
     }
     
     /*
     * Plays the sound file associated with the specified word.
     * The word is looked up in the dictionary, and if a sound file is associated,
     * it is played using a SoundPlayer.
     * @param word The word whose sound file is to be played.
     */
     private static void handleSound(String word) {
    	    Record record = dictionary.get(new Key(word, 3)); 
    	    if (record != null) {
    	        SoundPlayer soundPlayer = new SoundPlayer();
    	        try {
    	            soundPlayer.play(record.getDataItem());
    	        } catch (MultimediaException e) {
    	            System.out.println("There is no sound file for " + word);
    	        } catch (Exception e) {
    	            System.out.println("Error playing sound file: " + e.getMessage());
    	        }
    	    } else {
    	        System.out.println("There is no sound file for " + word);
    	    }
    }
    
     /*
     * Plays the music file associated with the specified word.
     * The word is looked up in the dictionary, and if a music file is associated,
     * it is played using a SoundPlayer.
     * @param word The word whose music file is to be played.
     */
     private static void handlePlay(String word) {
    	 Record record = dictionary.get(new Key(word, 4)); 
    	 if (record != null) {
    	        SoundPlayer soundPlayer = new SoundPlayer();
    	        try {
    	            soundPlayer.play(record.getDataItem());
    	        } catch (MultimediaException e) {
    	            System.out.println("There is no music file for " + word);
    	        } catch (Exception e) {
    	            System.out.println("Error playing music file: " + e.getMessage());
    	        }
    	    } else {
    	        System.out.println("There is no music file for " + word);
    	    }
     }
     
     /*
     * Plays the voice file associated with the specified word.
     * The word is looked up in the dictionary, and if a voice file is associated,
     * it is played using a SoundPlayer.
     * @param word The word whose voice file is to be played.
     */
     private static void handleSay(String word) {
    	 Record record = dictionary.get(new Key(word, 5)); 
    	 if (record != null) {
    	        SoundPlayer soundPlayer = new SoundPlayer();
    	        try {
    	            soundPlayer.play(record.getDataItem());
    	        } catch (MultimediaException e) {
    	            System.out.println("There is no voice file for " + word);
    	        } catch (Exception e) {
    	            System.out.println("Error playing voice file: " + e.getMessage());
    	        }
    	    } else {
    	        System.out.println("There is no voice file for " + word);
    	    }
     }
     
     /*
     * Displays the image file associated with the specified word.
     * The word is looked up in the dictionary, and if an image file is associated,
     * it is displayed using a PictureViewer.
     * @param word The word whose image file is to be shown.
     * @throws MultimediaException If there is an issue displaying the image.
     */
     private static void handleShow(String word) throws MultimediaException {
    	 Record record = dictionary.get(new Key(word, 6)); 
    	    if (record != null) {
    	    	PictureViewer pictureViewer = new PictureViewer();
    	        pictureViewer.show(record.getDataItem());
    	    }    
    	    else {
    	        System.out.println("There is no image file for " + word);
    	 }
    }

     /*
     * Displays the animated image file associated with the specified word.
     * The word is looked up in the dictionary, and if an animated image file is associated,
     * it is displayed using a PictureViewer.
     * @param word The word whose animated image file is to be shown.
     * @throws MultimediaException If there is an issue displaying the animated image.
     */
     private static void handleAnimate(String word) throws MultimediaException {
    	Record record = dictionary.get(new Key(word, 7)); 
	 	    if (record != null) {
	 	    	PictureViewer pictureViewer = new PictureViewer();
	 	        pictureViewer.show(record.getDataItem());
	 	        
	 	        }
	 	    else {
	 	        System.out.println("There is no animated image file for " + word);
	 	 }
     }
     
     /*
     * Opens a browser window to display the webpage associated with the specified word.
     * The word is looked up in the dictionary, and if a webpage URL is associated,
     * it is opened in a web browser.
     * @param word The word whose associated webpage is to be browsed.
     * @throws MultimediaException If there is an issue opening the webpage.
     */
     private static void handleBrowse(String word) throws MultimediaException {
    	    Record record = dictionary.get(new Key(word, 8)); 
    	    if (record != null) {
    	    	ShowHTML showHTML = new ShowHTML();
    	        showHTML.show(record.getDataItem());
    	    } else {
    	        System.out.println("There is no webpage called " + word);
    	    }
    	}

     /*
     * Deletes a record from the dictionary.
     * The record is identified using the provided label and type,
     * and then removed from the dictionary.
     * @param label The label of the record to delete.
     * @param type The type of the record to delete.
     */
     private static void handleDelete(String label, int type) {
    	    try {
    	        dictionary.remove(new Key(label.toLowerCase(), type));
    	        System.out.println("Record removed successfully");
    	    } catch (DictionaryException e) {
    	        System.out.println("No record in the ordered dictionary has key (" + label + ", " + type + ")");
    	    }
     }
     
     /*
     * Adds a new record to the dictionary.
     * The record is created using the provided label, type, and data,
     * and then added to the dictionary.
     * @param label The label of the new record.
     * @param type The type of the new record.
     * @param data The data associated with the new record.
     */
     private static void handleAdd(String label, int type, String data) {
    	    try {
    	        dictionary.put(new Record(new Key(label.toLowerCase(), type), data));
    	        System.out.println("A record with the given key (" + label + ", " + type + ") is already in the ordered dictionary");
    	    } catch (DictionaryException e) {
    	        System.out.println("A record with the given key (" + label + ", " + type + ") is already in the ordered dictionary");
    	    }
    	}
     
     /*
     * Lists all records starting with a specific prefix.
     * Records in the dictionary that start with the given prefix are listed.
     * @param prefix The prefix to search for in the dictionary.
     */
     private static void handleList(String prefix) {
         List<Record> records = dictionary.getRecordsStartingWith(prefix);

         if (records.isEmpty()) {
             System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
         } else {
             for (Record record : records) {
                 System.out.println(record.getKey().getLabel());
             }
         }
     }
     
     /*
     * Displays the first record in the dictionary.
     * The first record is determined based on some ordering in the dictionary.
     */
     private static void handleFirst() {
    	    Record record = dictionary.smallest();
    	    if (record != null) {
    	        System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
    	    } else {
    	        System.out.println("Dictionary is empty.");
    	    }
    }
     
	/*
	* Displays the last record in the dictionary.
	* The last record is determined based on some ordering in the dictionary.
	*/
    private static void handleLast() {
	    Record record = dictionary.largest();
	    if (record != null) {
	        System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
	    } else {
	        System.out.println("Dictionary is empty.");
	    }
	}

    /*
    * Exits the program.
    * Terminates the execution of the application.
    */
    private static void handleExit() {
	    System.out.println("Exiting program.");
	    System.exit(0);
	}
     
     /*
     * Combines parts of a string array into a single string.
     * Useful for reconstructing command arguments split into parts.
     * @param parts The array of string parts to combine.
     * @param startIndex The starting index in the array to begin combining.
     * @return The combined string.
     */
     private static String combineParts(String[] parts, int startIndex) {
    	    StringBuilder builder = new StringBuilder();
    	    for (int i = startIndex; i < parts.length; i++) {
    	        if (i > startIndex) {
    	            builder.append(" ");
    	        }
    	        builder.append(parts[i]);
    	    }
    	    return builder.toString();
    	}
    
}