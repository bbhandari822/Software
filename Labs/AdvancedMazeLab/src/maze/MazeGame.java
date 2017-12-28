/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */

public class MazeGame {

	public Maze createMaze(final String path, MazeFactory mazeFactory){

		Maze maze = mazeFactory.MakeMaze();
		System.out.println("The maze does not have any rooms yet!");

		if(path.equals("")){
			Room room1 = mazeFactory.MakeRoom(0);
			Room room2 = mazeFactory.MakeRoom(1);
			Door door = mazeFactory.MakeDoor(room1,room2);

			maze.addRoom(room1);
			maze.addRoom(room2);
			maze.setCurrentRoom(room1);

			room1.setSide(Direction.North,mazeFactory.MakeWall());
			room1.setSide(Direction.East,door);
			room1.setSide(Direction.West,mazeFactory.MakeWall());
			room1.setSide(Direction.South,mazeFactory.MakeWall());
			room2.setSide(Direction.West,door);
			room2.setSide(Direction.East,mazeFactory.MakeWall());
			room2.setSide(Direction.North,mazeFactory.MakeWall());
			room2.setSide(Direction.South,mazeFactory.MakeWall());

		}else {
			Map<String, Door> doorHashMap = new HashMap<>();
			try {

				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				String currentLine;

				while ((currentLine = bufferedReader.readLine()) != null) {
					String[] split = currentLine.split("\\s+");
					switch (split[0]) {
						case "door":
							Door door1 = mazeFactory.MakeDoor(maze.getRoom(Integer.parseInt(split[2])), maze.getRoom(Integer.parseInt(split[3])));
							if (split[4].equals("open")) {
								door1.setOpen(true);
							}
							doorHashMap.put(split[1], door1);
							break;
						case "room":
							Room room1 = mazeFactory.MakeRoom(Integer.parseInt(split[1]));
							maze.addRoom(room1);
							break;
					}
				}
				bufferedReader.close();

				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(path));
				String currentLine1;

				while ((currentLine1 = bufferedReader1.readLine()) != null) {
					String[] split = currentLine1.split("\\s+");
					if (split[0].equals("room")) {
						Room room1 = maze.getRoom(Integer.parseInt(split[1]));

						if(split[2].charAt(0) == 'd'){
							room1.setSide(Direction.North, doorHashMap.get(split[2]));
						}else if(split[2].equals("wall")){
							room1.setSide(Direction.North, mazeFactory.MakeWall());
						}else {
							room1.setSide(Direction.North, maze.getRoom(Integer.parseInt(split[2])));
						}

						if(split[3].charAt(0) == 'd'){
							room1.setSide(Direction.South, doorHashMap.get(split[3]));
						}else if(split[3].equals("wall")){
							room1.setSide(Direction.South, mazeFactory.MakeWall());
						}else {
							room1.setSide(Direction.South, maze.getRoom(Integer.parseInt(split[3])));
						}

						if(split[4].charAt(0) == 'd'){
							room1.setSide(Direction.East, doorHashMap.get(split[4]));
						}else if(split[4].equals("wall")){
							room1.setSide(Direction.East, mazeFactory.MakeWall());
						}else {
							room1.setSide(Direction.East, maze.getRoom(Integer.parseInt(split[4])));
						}

						if(split[5].charAt(0) == 'd'){
							room1.setSide(Direction.West, doorHashMap.get(split[5]));
						}else if(split[5].equals("wall")){
							room1.setSide(Direction.West, mazeFactory.MakeWall());
						}else {
							room1.setSide(Direction.West, maze.getRoom(Integer.parseInt(split[5])));
						}
					}

				}

			}catch (IOException e) {
				e.printStackTrace();
			}
			maze.setCurrentRoom(maze.getRoom(0));
		}


		return maze;
	}

	public static void main(String[] args){

		MazeFactory mazeFactory = new MazeFactory();
		Maze maze = new Maze();
		MazeGame mazeGame = new MazeGame();

		if(args.length ==2){
			if(args[0].equals("red")){
				RedMazeFactory redMazeFactory = new RedMazeFactory();
				//mazeFactory = new RedMazeFactory();
				maze = mazeGame.createMaze(args[1],redMazeFactory);
			}else{
				BlueMazeFactory blueMazeFactory = new BlueMazeFactory();
				mazeFactory = new BlueMazeFactory();
				maze = mazeGame.createMaze(args[1],mazeFactory);
			}
		}else if(args.length == 1){
			if(args[0].equals("red")){
				mazeFactory = new RedMazeFactory();
                maze = mazeGame.createMaze("",mazeFactory);
			}else{
				mazeFactory = new BlueMazeFactory();
				maze = mazeGame.createMaze("",mazeFactory);
			}
		}
		maze.setCurrentRoom(0);
		MazeViewer mazeViewer = new MazeViewer(maze);
		mazeViewer.run();
	}

}
