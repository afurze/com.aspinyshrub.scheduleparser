/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspinyshrub.scheduleparser;

import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author furze
 */
public class Game {
    public CSVRecord record;
    
    public Game(CSVRecord record) {
        this.record = record;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Game) {
            Game toCompare = (Game) o;
            return this.record.get(0).equals(toCompare.record.get(0));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return record.get(0).hashCode();
    }
}
