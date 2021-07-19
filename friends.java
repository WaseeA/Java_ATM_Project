/* This file contains the graph class which organizes the friends.
 *
 * Copyright and Usage Information
 * This file is provided solely for the personal and private use
 * for non profit purposes. All forms of distribution of this code
 * are expressly prohibited. For more information please contact me
 * at my GitHub @WaseeA.
 *
 * This file is Copyright (c) Wasee Alam.
 * */

import java.io.*;
import java.util.*;

public class friends extends transactions{
    /*
    This class represents a graph, each key is a person, and the linked list is a
    list of other vertex nodes the person is connected to.
     */
    // create a hashmap to store the vertex and the list of
    // vertices it's connected to
    protected static Map<Integer, List<Integer> > map = new HashMap<>();

    public static void saveData() throws IOException {
        try {
            FileOutputStream myFileOutStream
                    = new FileOutputStream(
                    "data/friends.txt");

            ObjectOutputStream myObjectOutStream
                    = new ObjectOutputStream(myFileOutStream);

            myObjectOutStream.writeObject(map);

            // closing FileOutputStream and
            // ObjectOutputStream
            myObjectOutStream.close();
            myFileOutStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, List<Integer>> loadData() throws IOException {
        //https://www.geeksforgeeks.org/how-to-serialize-hashmap-in-java/
        HashMap<Integer, List<Integer>> newHashMap = null;

        try {
            FileInputStream fileInput = new FileInputStream(
                    "data/friends.txt");

            ObjectInputStream objectInput
                    = new ObjectInputStream(fileInput);

            newHashMap = (HashMap)objectInput.readObject();

            objectInput.close();
            fileInput.close();
        }

        catch (IOException obj1) {
            obj1.printStackTrace();
            return newHashMap;
        }

        catch (ClassNotFoundException obj2) {
            System.out.println("Class not found");
            obj2.printStackTrace();
            return null;
            }
        return newHashMap;
    }

    public void addPerson(bank account) {
        // insert the person (a vertex) into the linked list
        map.put(account.UID, new LinkedList<Integer>());
    }

    public void addFriend(bank src, bank dest) throws IOException {
        // Insert an edge between friend 1 and friend 2 (src & dest)
        // First check if src is in the map
        if (!map.containsKey(src.UID)) {
            addPerson(src); }
        // Check if dest is in the map
        if (!map.containsKey(dest.UID)) {
            addPerson(dest);}
        // Now add an edge
        map.get(src.UID).add(dest.UID);
        map.get(dest.UID).add(src.UID);
        saveData();
    }

    public static ArrayList<Integer> seeFriends(bank src) throws IOException {
        HashMap<Integer, List<Integer>> map1 = loadData();
        ArrayList<Integer> friendList = new ArrayList<Integer>();
        for (int i = 0; i < Objects.requireNonNull(map1).size() - 1; i++) {
            friendList.add(map1.get(src.UID).get(i));
        }
        return friendList;
    }

    public static void eTransfer(bank src, bank dest, Double amount) throws FileNotFoundException {
        /*
        This function serves to locate the two users and facilitate a transfer of funds.
         */
        transactions.transaction(src.UID, amount, "withdraw");
        transactions.transaction(dest.UID, amount, "deposit");
    }
}
