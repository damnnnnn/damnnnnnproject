package com.music.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMusic  extends Remote
{
	
	public String   queryMusicInfo(String musicName) throws  RemoteException;

}
