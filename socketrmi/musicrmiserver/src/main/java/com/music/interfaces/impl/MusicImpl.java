package com.music.interfaces.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.music.interfaces.IMusic;
import com.music.interfaces.db.DB;

public class MusicImpl  extends UnicastRemoteObject  implements  IMusic
{

	public MusicImpl() throws RemoteException
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public String queryMusicInfo(String musicName) throws RemoteException
	{
		System.out.println("MusicImpl  is  queryMusicInfo  start... "+musicName);
		// TODO Auto-generated method stub
		
		DB db = new DB();
		String  musicInfo=db.queryByNameMusic(musicName);
		
		return musicInfo;
	}

}
