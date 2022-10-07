package com.rubon.mineminer;

public abstract class AndroidUnityCommunicator
{
    public static AndroidUnityCommunicator instance = null;

    abstract protected boolean canFarmBlock(String ore);

    public AndroidUnityCommunicator(){
        instance = this;
    }
}
