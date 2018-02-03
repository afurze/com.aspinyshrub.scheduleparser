/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspinyshrub.scheduleparser;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.*;

/**
 *
 * @author furze
 */
public class Parser {
    private static CSVParser originalParser;
    private static CSVParser newParser;
    private static ArrayList<Game> originalList = new ArrayList<Game>();
    private static ArrayList<Game> newList = new ArrayList<Game>();
    
    public static ArrayList<String> Parse(String originalFilePath, String newFilePath){
        ArrayList<String> results = new ArrayList<String>();

        // load parsers
        try {
            GetCSVParsers(originalFilePath, newFilePath);
            GetLists();
            results = CompareLists();
        } catch (IOException e) {
            results.add("Error reading files");
        }
        
        return results;
    }
    
    private static void GetCSVParsers(String originalFilePath, String newFilePath) throws IOException {
        File origionalFile = new File(originalFilePath);
        File newFile = new File(newFilePath);
        originalParser = CSVParser.parse(origionalFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
        newParser = CSVParser.parse(newFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
    }
    
    private static void GetLists() throws IOException {
        //origionalList = originalParser.getRecords();
        //newList = newParser.getRecords();
        
        for (CSVRecord record : originalParser.getRecords()) {
            originalList.add(new Game(record));
        }
        originalParser.close(); // done with this now
        
        for (CSVRecord record : newParser.getRecords()) {
            newList.add(new Game(record));
        }
        newParser.close(); // done with this too
    }
    
    private static ArrayList<String> CompareLists() {
        ArrayList<String> results = new ArrayList<String>();
        
        // loop thru new game list comparing to old
        for (Game game : newList) {
            if (originalList.contains(game)) { // game from new list already existed
                
            } else { // new game
                results.add("New game: " + game.record.get(0));
            }
        }
        
        // finally check for removed games
        for (Game game : originalList) {
            if (!newList.contains(game)) {
                results.add("Removed game: " + game.record.get(0));
            }
        }
        
        return results;
    }
}
