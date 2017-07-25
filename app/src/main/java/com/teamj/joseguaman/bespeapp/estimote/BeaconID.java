package com.teamj.joseguaman.bespeapp.estimote;

import com.estimote.sdk.Region;

import java.util.UUID;

public class BeaconID {

    private UUID proximityUUID;
    private int major;
    private int minor;
    private int claveBase;

    public BeaconID(UUID proximityUUID, int major, int minor) {
        this.proximityUUID = proximityUUID;
        this.major = major;
        this.minor = minor;
    }

    public BeaconID(String UUIDString, int major, int minor) {
        this(UUID.fromString(UUIDString), major, minor);
    }

    public BeaconID(int claveBase, String UUIDString, int major, int minor) {
        this(UUID.fromString(UUIDString), major, minor);
        this.claveBase=claveBase;
    }

    public UUID getProximityUUID() {
        return proximityUUID;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getClaveBase() {
        return claveBase;
    }

    public void setClaveBase(int claveBase) {
        this.claveBase = claveBase;
    }

    public Region toBeaconRegion() {
        return new Region(toString(), getProximityUUID(), getMajor(), getMinor());
    }

    public String toString() {
        return getProximityUUID().toString() + ":" + getMajor() + ":" + getMinor();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return super.equals(o);
        }

        BeaconID other = (BeaconID) o;

        return getProximityUUID().equals(other.getProximityUUID())
                && getMajor() == other.getMajor()
                && getMinor() == other.getMinor();
    }
}
