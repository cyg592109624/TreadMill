package com.sunrise.treadmill.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class WorkOut implements Parcelable {
    private int workOutMode;
    private String workOutModeName;
    /**
     * 0代表男；1代表女
     */
    private int gender;
    private int age;
    private int weight;
    private int time;
    private int distance;
    private int hrc60;
    private int htc80;
    private int targetHR;
    private List<Level> levelList;
    private Level currentLevel;

    protected WorkOut(Parcel in) {
        workOutMode = in.readInt();
        workOutModeName = in.readString();
        gender = in.readInt();
        age = in.readInt();
        weight = in.readInt();
        time = in.readInt();
        distance = in.readInt();
        hrc60 = in.readInt();
        htc80 = in.readInt();
        targetHR = in.readInt();
    }

    public static final Creator<WorkOut> CREATOR = new Creator<WorkOut>() {
        @Override
        public WorkOut createFromParcel(Parcel in) {
            return new WorkOut(in);
        }

        @Override
        public WorkOut[] newArray(int size) {
            return new WorkOut[size];
        }
    };

    public int getWorkOutMode() {
        return workOutMode;
    }

    public void setWorkOutMode(int workOutMode) {
        this.workOutMode = workOutMode;
    }

    public String getWorkOutModeName() {
        return workOutModeName;
    }

    public void setWorkOutModeName(String workOutModeName) {
        this.workOutModeName = workOutModeName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        if (gender == 0) {
            this.gender = 0;
        } else {
            this.gender = 1;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHrc60() {
        return hrc60;
    }

    public void setHrc60(int hrc60) {
        this.hrc60 = hrc60;
    }

    public int getHtc80() {
        return htc80;
    }

    public void setHtc80(int htc80) {
        this.htc80 = htc80;
    }

    public int getTargetHR() {
        return targetHR;
    }

    public void setTargetHR(int targetHR) {
        this.targetHR = targetHR;
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(workOutMode);
        parcel.writeString(workOutModeName);
        parcel.writeInt(gender);
        parcel.writeInt(age);
        parcel.writeInt(weight);
        parcel.writeInt(time);
        parcel.writeInt(distance);
        parcel.writeInt(hrc60);
        parcel.writeInt(htc80);
        parcel.writeInt(targetHR);
        //34651321
    }
}
