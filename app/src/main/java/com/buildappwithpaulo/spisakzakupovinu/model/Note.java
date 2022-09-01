package com.buildappwithpaulo.spisakzakupovinu.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Note  implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String namirnica;

    private String kolicina;

    private int redosled;

    public Note(String namirnica, String kolicina, int redosled) {
        this.namirnica = namirnica;
        this.kolicina = kolicina;
        this.redosled = redosled;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        namirnica = in.readString();
        kolicina = in.readString();
        redosled = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void setNamirnica(String namirnica) {
        this.namirnica = namirnica;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamirnica() {
        return namirnica;
    }

    public String getKolicina() {
        return kolicina;
    }

    public int getRedosled() {
        return redosled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(namirnica);
        parcel.writeString(kolicina);
        parcel.writeInt(redosled);
    }
}
