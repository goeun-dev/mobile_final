package com.example.user.a2a_216230029_leegoeun;

/**
 * Created by user on 2017-12-03.
 */

public class Voca {

    private int _id;
    private String _word;
    private String _mean;

    public Voca() {
    }

    public Voca(int id, String word, String mean) {
        this._id = id;
        this._word = word;
        this._mean = mean;
    }

    public Voca(String word, String mean) {
        this._word = word;
        this._mean = mean;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setWord(String word) {
        this._word = word;
    }

    public String getWord() {
        return this._word;
    }

    public void setMean(String mean) {
        this._mean = mean;
    }

    public String getMean() {
        return this._mean;
    }
}