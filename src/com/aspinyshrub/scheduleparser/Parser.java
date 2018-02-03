/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspinyshrub.scheduleparser;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.*;

/**
 *
 * @author furze
 */
public class Parser {
    private static CSVParser origionalParser;
    private static CSVParser newParser;
    private static List<CSVRecord> origionalList;
    private static List<CSVRecord> newList;
    
    public static ArrayList<String> Parse(String origionalFilePath, String newFilePath){
        ArrayList<String> results = new ArrayList<String>();

        // load parsers
        try {
            GetCSVParsers(origionalFilePath, newFilePath);
            //CompareCSVParsers();
        } catch (IOException e) {
            results.add("Error reading files");
        }
        
        return results;
    }
    
    private static void GetCSVParsers(String origionalFilePath, String newFilePath) throws IOException {
        File origionalFile = new File(origionalFilePath);
        File newFile = new File(newFilePath);
        origionalParser = CSVParser.parse(origionalFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
        newParser = CSVParser.parse(newFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
    }
    
    //private static ArrayList<String> CompareCSVParsers() {
        // loop thru new file looking for changes
        
    //}
}
