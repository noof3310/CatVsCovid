package logic;

import entity.Alcohol;

import entity.Corona;
import entity.Door;
import entity.GlassShard;
import entity.Key;
import entity.Lantern;
import entity.Medicine;
import entity.Player;
import entity.Suit;
import entity.Vaccine;
import entity.Wall;
import entity.base.Collectable;
import entity.base.Entity;
import entity.base.HurtPlayerable;
import entity.base.Impassable;
import gui.DarkFog;
import gui.GameFinishedCanvas;
import gui.GamePane;
import gui.SoundController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.exception.GameStatusNotFoundException;
import logic.exception.MapCodeInvalidException;

public class GameController {
	
	private static GamePane gamePane;
	private static ObservableList<Entity> objects = FXCollections.observableArrayList();

	private static DarkFog fog;
	private static Player player;
	private static double hp;
	
	private static boolean gamePlayable;
	private static int alcoholCount;
	private static int keyCount;
	private static final double MAX_HP = 10;
	

	public static void InitializeGameBoard(String mapUrl) throws MapCodeInvalidException {
		
		gamePane = new GamePane();
		
		objects.clear();
		gamePlayable = true;
		
		Canvas floorCanvas = new Canvas(2000, 2000);
		GraphicsContext floorGc = floorCanvas.getGraphicsContext2D();
		floorGc.drawImage(new Image(ClassLoader.getSystemResource("img/Floor.png").toString()), 0, 0);
		gamePane.getChildren().add(floorCanvas);
		
		MapCode map = new MapCode(mapUrl);
		char[][] mapCode = map.getMapCode();
		
		for(int row = 0; row < 25; row++) {
			for(int col = 0; col < 30; col++) {
				
				switch(mapCode[row][col]) {
				case 'B':
					break;
				case 'W':
					objects.add(new Wall(col, row));
					break;
				case 'V':
					objects.add(new Vaccine(col,row));
					break;
				case 'C':
					objects.add(new Corona(col, row));
					break;
				case 'A':
					objects.add(new Alcohol(col, row));
					break;
				case 'M':
					objects.add(new Medicine(col, row));
					break;
				case 'L':
					objects.add(new Lantern(col, row));
					fog = new DarkFog(col, row);
					break;
				case 'G':
					objects.add(new GlassShard(col, row));
					break;
				case 'K':
					objects.add(new Key(col, row));
					break;
				case 'D':
					objects.add(new Door(col,row));
					break;
				case 'S':
					objects.add(new Suit(col,row));
					break;
				case 'P':
					player = new Player(col, row);
					break;
				default:
					throw new MapCodeInvalidException(mapCode[row][col], row, col);
				}
			}
		}
		
		for(Entity thing : objects) {
			if(!(thing instanceof Corona)) gamePane.getChildren().add(thing);
		}
		
		for(Entity thing : objects) {
			if(thing instanceof Corona) gamePane.getChildren().add(thing);
		}
		
		gamePane.getChildren().add(player);
		setHp(10);
		setAlcoholCount(0);
		setKeyCount(0);
		SoundController.playPlayerMove();
		
		gamePane.getChildren().add(fog);
		
		for(Entity thing : objects) {
			if(thing instanceof Corona) moveCorona((Corona) thing);
		}
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(gamePlayable == true) {
					SoundController.playSong();
				}
				SoundController.stopPlaySong();
			}
		});
		t.start();
		
	}
	
	public static void movePlayer(Direction direction) {
		
		boolean possible = true;
		
		if(!isGamePlayable()) return;
		
		player.move(direction);
		
		try {
			
			for(Entity thing : objects) {
				
				if(thing.getBoundsInParent().intersects(player.getBoundsInParent())) {
					
					if(thing instanceof Impassable) {
						possible = false;
						if(thing instanceof Door && useKey()) {
							removeEntity(thing);
							possible = true;
						}
					}else if(thing instanceof Collectable) {
						((Collectable) thing).collect();
						if(thing instanceof Suit) SoundController.playLevelUp();
						else SoundController.playCollected();
					}else if(thing instanceof HurtPlayerable) {
						if(((HurtPlayerable) thing).hit()) SoundController.playHurt();
					}
					
				}
				
			}
			
		} catch(Exception e) {
			
		}
		
		if(possible) SoundController.playPlayerMove();
		else {
			
			switch(direction) {
			case LEFT:
				player.move(Direction.RIGHT);
				break;
			case RIGHT:
				player.move(Direction.LEFT);
				break;
			case UP:
				player.move(Direction.DOWN);
				break;
			case DOWN:
				player.move(Direction.UP);
				break;
			default:
				break;
			}
			
			player.setDirection(direction);
		}
		
		if(player.isHoldingLantern()) fog.followingPlayer();
		
	}
	
	public static void moveCorona(Corona corona) {
		
		AnimationTimer t = new AnimationTimer() {
			
			private long lastUpdate = 0;
			private long timePerMove = (long)(Math.random()*(500_000_000)+200_000_000);
			
			@Override
			public void handle(long now) {
				
				if(!corona.isAlive() || !isGamePlayable()) this.stop();
				
				if(now-lastUpdate >= timePerMove) {
					
					boolean possible = true;
					Direction dir;
					
					switch((int)(Math.random()*(4)+1)) {
					default:
					case 1:
						dir = Direction.LEFT;
						break;
					case 2:
						dir = Direction.RIGHT;
						break;
					case 3:
						dir = Direction.UP;
						break;
					case 4:
						dir = Direction.DOWN;
						break;
					}
					
					corona.move(dir);
					
					if(player.getBoundsInParent().intersects(corona.getBoundsInParent())) {
						if(corona.hit());
							SoundController.playHurt();
					}

					for(Entity thing : objects) {
						if(thing.getBoundsInParent().intersects(corona.getBoundsInParent())) {
							if(thing instanceof Impassable) {
								possible = false;
							}
						}
					}
					
					if(!possible) {
						switch(dir) {
						case LEFT:
							corona.move(Direction.RIGHT);
							break;
						case RIGHT:
							corona.move(Direction.LEFT);
							break;
						case UP:
							corona.move(Direction.DOWN);
							break;
						case DOWN:
							corona.move(Direction.UP);
							break;
						default:
							break;
						}
					}
					
					lastUpdate = now;
				}

			}
		};
		
		t.start();
		
	}
	
	public static void removeEntity(Entity e) {
		gamePane.getChildren().remove(e);
		objects.remove(e);
	}
	
	public static void changePlayerSuit() {
		player.setWearingSuit(true);
	}
	
	public static GamePane getGamePane() {
		return gamePane;
	}

	public static void setGamePane(GamePane gamePane) {
		GameController.gamePane = gamePane;
	}

	public static double getHp() {
		return hp;
	}

	public static void setHp(double hp) {
		GameController.hp = Math.min(hp, MAX_HP);
		GameController.hp = Math.max(0, GameController.hp);
		if(GameController.hp == 0) setGame("Lose");
	}
	
	public static void increaseHp(double hp) {
		setHp(getHp() + hp);
	}
	
	public static double getMaxHp() {
		return MAX_HP;
	}
	
	public static int getAlcoholCount() {
		return alcoholCount;
	}

	public static void setAlcoholCount(int alcoholCount) {
		GameController.alcoholCount = Math.max(0, alcoholCount);
	}
	
	public static boolean useAlcohol() {
		if(getAlcoholCount() == 0) return false;
		else{
			setAlcoholCount(getAlcoholCount()-1);
			SoundController.playShield();
			return true;
		}
	}
	
	public static int getKeyCount() {
		return keyCount;
	}

	public static void setKeyCount(int keyCount) {
		GameController.keyCount = Math.max(0, keyCount);
	}
	
	public static boolean useKey() {
		if(getKeyCount() == 0) return false;
		else{
			setKeyCount(getKeyCount()-1);
			return true;
		}
	}

	public static void setGame(String status) {
		
		GameFinishedCanvas finishedCanvas;
		
		try {
			
			finishedCanvas = new GameFinishedCanvas(gamePane.getWidth(), gamePane.getHeight(), status);
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								gamePane.getChildren().add(finishedCanvas);
								if(status == "Win") SoundController.playWin();
								else if(status == "Lose") SoundController.playOver();
								setGamePlayable(false);
							}
							
						});
				}
			});
			
			t.start();
			
		} catch (GameStatusNotFoundException e1) {
			System.out.println("There are only 2 status, \"Win\" or \"Lose\"");
		}
		
	}

	public static boolean isGamePlayable() {
		return gamePlayable;
	}

	public static void setGamePlayable(boolean gamePlayable) {
		GameController.gamePlayable = gamePlayable;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		GameController.player = player;
	}

	public static ObservableList<Entity> getObjects() {
		return objects;
	}

	public static void setObjects(ObservableList<Entity> objects) {
		GameController.objects = objects;
	}

	public static DarkFog getFog() {
		return fog;
	}

	public static void setFog(DarkFog fog) {
		GameController.fog = fog;
	}
	
	
}
