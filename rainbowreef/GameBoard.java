package rainbowreef;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class GameBoard extends JPanel implements KeyListener, Runnable {

    private static int windowWidth = 640;
    private static int windowHeight = 480;
    private static int gameLevel = 1;
    private static int numOfOctopuses = 0;
    private static int highScore = 0;
    private static String gameStatusInfo = "";
    private static int gameStatus = 0;
    private static ArrayList<StarFish> starFishes = new ArrayList<>();

    private ArrayList<Block> blocks;
    private SeaSnail seaSnail;

    public GameBoard() {
        blocks = new ArrayList<>();
        seaSnail = new SeaSnail(280, 430);
        this.init();
    }

    public final void init() {
        int wallWidth = GameObject.getImg("Wall").getWidth();
        int wallHeight = GameObject.getImg("Wall").getHeight();
        int column = windowWidth / wallWidth;
        int row = windowHeight / wallHeight;

        // generate walls (borders)
        for (int i = 0; i < row; ++i) {
            blocks.add(new Wall(0 * wallWidth, i * wallHeight));
            blocks.add(new Wall((column - 1) * wallWidth, i * wallHeight));
        }
        for (int j = 1; j < column - 1; ++j) {
            blocks.add(new Wall(j * wallWidth, 0 * wallHeight));
        }

        // generate blocks (including reefs and powerups)
        // generate octopuses
        int blockWidth = GameObject.getImg("Block").getWidth();
        int blockHeight = GameObject.getImg("Block").getHeight();
        for (int i = 1; i < 10; ++i) {
            for (int j = 0; j < 15; ++j) {
                if (i < 3) {
                    if (isColumnForBlock(j)) {
                        generateNonReef(wallWidth + j * blockWidth, i * blockHeight, "Block");
                    } else if (isColumnForOctopus(j)) {
                        if (isRowForOctopus(i)) {
                            generateNonReef(wallWidth + j * blockWidth, i * blockHeight, "Octopus");
                            numOfOctopuses++;
                        }
                    } else {
                        if ((i + j) % 2 == 0) {
                            generateNonReef(wallWidth + j * blockWidth, i * blockHeight, "ExtraLife");
                        } else {
                            generateNonReef(wallWidth + j * blockWidth, i * blockHeight, "ExtraScore");
                        }
                    }
                } else {
                    if (isColumnForBlock(j)) {
                        generateNonReef(wallWidth + j * blockWidth, i * blockHeight, "Block");
                    } else {
                        Random rand = new Random();
                        generateReef(wallWidth + j * blockWidth, i * blockHeight, rand.nextInt(100) % 7 + 1);
                    }
                }
            }
        }

        // generate starfish
        generateStarFish(seaSnail.getX() + 35, 205);
    }

    public static void generateStarFish(int x, int y) {
        StarFish starFish = new StarFish(x, y, gameLevel);
        if (StarFish.getNumOfLives() > 0) {
            starFishes.add(starFish);
            Thread starFishThread = new Thread(starFish);
            starFishThread.start();
        }
    }

    private void generateReef(int x, int y, int level) {
        blocks.add(new Reef(x, y, level));
    }

    private void generateNonReef(int x, int y, String blockName) {
        switch (blockName) {
            case "Octopus":
                blocks.add(new Octopus(x, y));
                break;
            case "ExtraScore":
                blocks.add(new ExtraScore(x, y));
                break;
            case "ExtraLife":
                blocks.add(new ExtraLife(x, y));
                break;
            case "ExtraStarFish":
                blocks.add(new ExtraStarFish(x, y));
                break;
            default:
                blocks.add(new Block(x, y));
                break;
        }
    }

    private boolean isColumnForBlock(int column) {
        return column == 2 || column == 5 || column == 9 || column == 12;
    }

    private boolean isColumnForOctopus(int column) {
        return column % (6 / gameLevel) == 1;
    }

    private boolean isRowForOctopus(int row) {
        return row % 2 == 1;
    }

    private void checkCollision(Graphics g) {
        StarFish starFish;
        for (int i = 0; i < starFishes.size(); ++i) {
            starFish = starFishes.get(i);
            if (collide(seaSnail, starFish)) {
                return;
            }
            for (int j = 0; j < blocks.size(); ++j) {
                if (collide(blocks.get(j), starFish)) {
                    return;
                }
            }
        }
    }

    private boolean collide(Collidable obj1, Collidable obj2) {
        if (obj1.getBounds().intersects(obj2.getBounds())) {
            obj1.collide(obj2);
            obj2.collide(obj1);
            return true;
        } else {
            return false;
        }
    }

    private void loadMap(Graphics g) {
        loadBackground(g);
        loadBlock(g);
        loadSeaSnail(g);
        loadPlayerInfo(g);
        loadStarFish(g);
        checkCollision(g);
    }

    private void loadBlock(Graphics g) {
        Block block;
        for (int i = 0; i < blocks.size(); ++i) {
            block = blocks.get(i);
            if (block.isAlive()) {
                g.drawImage(Block.getImg(block.getBlockName()), block.getX(), block.getY(), block.getWidth(), block.getHeight(), null);
            } else {
                blocks.remove(i);
                if (block instanceof Octopus) {
                    numOfOctopuses--;
                }
            }
        }
    }

    private void loadBackground(Graphics g) {
        BufferedImage background;
        if (gameStatus == 2) {
            background = GameObject.getImg("Congratulation");
        } else {
            background = GameObject.getImg("Background" + gameLevel);
        }
        g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
    }

    private void loadSeaSnail(Graphics g) {
        g.drawImage(SeaSnail.getImg("SeaSnail"), seaSnail.getX(), seaSnail.getY(), seaSnail.getWidth(), seaSnail.getHeight(), null);
        seaSnail.update(null, seaSnail);
    }

    private void loadStarFish(Graphics g) {
        StarFish starFish;
        for (int i = 0; i < starFishes.size(); ++i) {
            starFish = starFishes.get(i);
            if (starFish.isAlive()) {
                g.drawImage(StarFish.getImg("StarFish"), starFish.getX(), starFish.getY(), starFish.getWidth(), starFish.getWidth(), null);
            } else {
                starFish.subtractNumOfLives();
                starFishes.remove(i);
                generateStarFish(seaSnail.getX() + 35, 205);
            }
        }
    }

    private void loadPlayerInfo(Graphics g) {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Lives ", 20, 475);
        g.drawString("Level " + gameLevel, 300, 475);
        g.drawString("Score " + StarFish.getScore(), 520, 475);
        BufferedImage lifeImg = GameObject.getImg("Life");
        for (int i = 0; i < StarFish.getNumOfLives(); ++i) {
            g.drawImage(lifeImg, 65 + i * lifeImg.getWidth(), 460, lifeImg.getWidth(), lifeImg.getHeight(), null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                seaSnail.toggleLeftPressed();
                break;
            case KeyEvent.VK_RIGHT:
                seaSnail.toggleRightPressed();
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                seaSnail.unToggleLeftPressed();
                break;
            case KeyEvent.VK_RIGHT:
                seaSnail.unToggleRightPressed();
                break;
            default:
                break;
        }
    }

    // check gaming status
    // if starfishes has no more life, losing condition satisfied
    // if octopuses has no more life, winning condition satisfied
    private void checkGameStatus() {
        if (gameStatus == 0) {
            int currScore = StarFish.getScore();
            highScore = currScore > highScore ? currScore : highScore;
            if (StarFish.getNumOfLives() == 0) {
                gameStatusInfo = "GAME OVER !  YOUR SCORE: " + highScore;
                gameStatus = 1;
            } else if (numOfOctopuses == 0) {
                if (gameLevel == 2) {
                    gameStatusInfo = "YOU WIN !  HIGH SCORE: " + highScore;
                    gameStatus = 2;
                } else {
                    gameLevel++;
                    for (int i = 0; i < starFishes.size(); ++i) {
                        starFishes.get(i).isAlive = false;
                    }
                    starFishes.clear();
                    blocks.clear();
                    init();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage board = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        Graphics rawMap = board.createGraphics();

        if (gameStatus != 0) {
            loadBackground(g);
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(gameStatusInfo, 180, 360);
        } else {
            loadMap(rawMap);
            BufferedImage miniMap = board.getSubimage(0, 0, windowWidth, windowHeight);
            g.drawImage(miniMap, 0, 0, windowWidth, windowHeight, null);
            g.dispose();
        }

        // check winning conditions
        checkGameStatus();
    }

    @Override
    public void run() {
        while (gameStatus == 0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                System.out.println("GameBoard Exception: " + ex.getMessage());
            }
            repaint();
        }
    }
}
