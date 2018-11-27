1. Version of Java
The version of Java used to build the game is 1.8.0_101.

2. Integrated Development Environment (IDE)
The IDE for Java is NetBeans 8.2 on Windows System.

3. Current Working Directory
The current working directory used for the game is this repo.

4. How to Run the Game
i). Clone the repo with the command: git clone https://github.com/jzhao11/rainbow_reef_game.
ii). Open the NetBeans IDE. Import the Project by clicking "File->New Project".
iii). Choose "Java Project with Existing Sources" in the "Projects" choice box and click "Next".
Specify a name (e.g. csc413_reef_jzhao11) and location for the new project and click "Next".
iv). Click "Add Folder" next to the "Source Package Folder" choice box.
In the pop-up, find and choose the local "csc413-secondgame-jzhao11" repo.
Click "Open" and the repo path will appear in the upper box. Then click "Next".
v). All the *.java files and related resources in the "Included Files" box are needed.
Click "Finish" to finish importing the project.
vi). To build the project, right click the imported project in the top-left "Project" box, and choose "Clean and Build".
This can also be done by clicking the "Clean and Build Project" button (Shift+F11) on the top navigation bar.
vii). To run the game, choose the imported project and click the "Run Project" button (Ctrl+F11) on the top navigation bar.

5. Controls for Playing the Game
SeaSnail (Katch): Left -> moving left; Right -> moving right.
There is no keyboard control for the starfish (Pop), and he can bounce freely around the area except the base. However, it can be controlled by Katch.
Basically, the horizontal movement of Katch will bounce Pop and prevent him from falling. The collision point along Katch’s shell will determine the direction of
Pop’s movement. Bounces on left half of Katch will send Pop left and bounces on right half will send Pop right. If bouncing at the center, Pop will move up vertically.
Every time after colliding with the Octopus (each Bigleg can be killed with 2 hits), Pop will move slightly faster, so that the game becomes increasingly difficult.
The game has 2 levels, with an initial of 3 lives. When running out of all the lives, the game is over.

6. Powerups in the Game
There are 2 types of powerups, extra life and extra score, which are generated at top of the game board to increase the difficulty of acquiring them.
The ExtraLife refers to the block with a heart in it. Each of them will add the lives count by 1 (at most 5 lives).
The ExtraScore refers to the block with purple center and white border. Each of them will add 500 bonus (much larger than other reefs) to the total score.
