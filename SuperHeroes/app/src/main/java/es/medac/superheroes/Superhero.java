package es.medac.superheroes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Superhero implements Parcelable {
    private String name;
    private String alterEgo;
    private String Bio;
    private float power;


    public Superhero(String alterEgo, String name, String bio, float power) {
        this.alterEgo = alterEgo;
        this.name = name;
        this.Bio = bio;
        this.power = power;
    }

    protected Superhero(Parcel in) {
        name = in.readString();
        alterEgo = in.readString();
        Bio = in.readString();
        power = in.readFloat();
    }

    public static final Creator<Superhero> CREATOR = new Creator<Superhero>() {
        @Override
        public Superhero createFromParcel(Parcel in) {
            return new Superhero(in);
        }

        @Override
        public Superhero[] newArray(int size) {
            return new Superhero[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public String getBio() {
        return Bio;
    }

    public float getPower() {
        return power;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(alterEgo);
        dest.writeString(Bio);
        dest.writeFloat(power);
    }
}
