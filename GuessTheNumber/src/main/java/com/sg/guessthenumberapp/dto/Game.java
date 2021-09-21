/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.dto;

import java.util.Objects;

/**
 *
 * @author pierre
 */
public class Game {

    private int id;

    private int ans1;
    private int ans2;
    private int ans3;
    private int ans4;



    private boolean isCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAns1() {
        return ans1;
    }

    public void setAns1(int ans1) {
        this.ans1 = ans1;
    }

    public int getAns2() {
        return ans2;
    }

    public void setAns2(int ans2) {
        this.ans2 = ans2;
    }

    public int getAns3() {
        return ans3;
    }

    public void setAns3(int ans3) {
        this.ans3 = ans3;
    }

    public int getAns4() {
        return ans4;
    }

    public void setAns4(int ans4) {
        this.ans4 = ans4;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.ans1;
        hash = 31 * hash + this.ans2;
        hash = 31 * hash + this.ans3;
        hash = 31 * hash + this.ans4;
        hash = 31 * hash + (this.isCompleted ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ans1 != other.ans1) {
            return false;
        }
        if (this.ans2 != other.ans2) {
            return false;
        }
        if (this.ans3 != other.ans3) {
            return false;
        }
        if (this.ans4 != other.ans4) {
            return false;
        }
        if (this.isCompleted != other.isCompleted) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", ans1=" + ans1 + ", ans2=" + ans2 + ", ans3=" + ans3 + ", ans4=" + ans4 + ", isCompleted=" + isCompleted + '}';
    }

}
