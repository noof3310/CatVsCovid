package gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;


public class SoundController {
	
	private static Media mediaPlayerMove = new Media(ClassLoader.getSystemResource("sound/Player_move.mp3").toString());
	private static MediaPlayer mediaPlayerPlayerMove = new MediaPlayer(mediaPlayerMove);

	private static Media mediaCollected = new Media(ClassLoader.getSystemResource("sound/Get_Item_Sound.mp3").toString());
	private static MediaPlayer mediaPlayerCollected = new MediaPlayer(mediaCollected);
	
	private static Media mediaHurt = new Media(ClassLoader.getSystemResource("sound/Take_Damage_Sound.mp3").toString());
	private static MediaPlayer mediaPlayerHurt = new MediaPlayer(mediaHurt);
	
	private static Media mediaShield = new Media(ClassLoader.getSystemResource("sound/Shield_Sound.mp3").toString());
	private static MediaPlayer mediaPlayerShield = new MediaPlayer(mediaShield);
	
	private static Media mediaLevelUp = new Media(ClassLoader.getSystemResource("sound/LevelUp_Sound.mp3").toString());
	private static MediaPlayer mediaPlayerLevelUp = new MediaPlayer(mediaLevelUp);
	
	private static Media mediaWin = new Media(ClassLoader.getSystemResource("sound/Win_Game_Sound.mp3").toString());
	private static MediaPlayer mediaPlayerWin = new MediaPlayer(mediaWin);
	
	private static Media mediaOver = new Media(ClassLoader.getSystemResource("sound/Game_Over_Sound_Ver2.mp3").toString());
	private static MediaPlayer mediaPlayerOver = new MediaPlayer(mediaOver);
	
	private static Media mediaSong = new Media(ClassLoader.getSystemResource("sound/Background_Song.mp4").toString());
	private static MediaPlayer mediaPlayerSong = new MediaPlayer(mediaSong);
	
	
	public static void playPlayerMove() {
		mediaPlayerPlayerMove.seek(new Duration(0));
		mediaPlayerPlayerMove.play();
	}
	

	public static void playCollected() {
		mediaPlayerCollected.seek(new Duration(0));
		mediaPlayerCollected.play();
	}
	
	public static void playHurt() {
		mediaPlayerHurt.seek(new Duration(0));
		mediaPlayerHurt.play();
	}
	
	public static void playShield() {
		mediaPlayerShield.seek(new Duration(0));
		mediaPlayerShield.play();
	}
	
	public static void playLevelUp() {
		mediaPlayerLevelUp.seek(new Duration(0));
		mediaPlayerLevelUp.play();
	}
	
	public static void playWin() {
		mediaPlayerWin.seek(new Duration(0));
		mediaPlayerWin.play();
	}
	
	public static void playOver() {
		mediaPlayerOver.seek(new Duration(0));
		mediaPlayerOver.play();
	}
	
	public static void playSong() {
		mediaPlayerSong.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayerSong.setVolume(0.2);
		mediaPlayerSong.play();
	}
	
	public static void stopPlaySong() {
		mediaPlayerSong.stop();
	}
	
	public static boolean getStatusPlaySong() {
		return mediaPlayerSong.getStatus().equals(Status.PLAYING);
	}

}