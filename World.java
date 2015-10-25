import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//
//
//
//
//
public class World{
	enum direction {down,left,up,right};
  public static void main(String[] args){
  	char[][] universe = new char[20][20];
  	for(int i = 0; i < 20; i++)
  		for(int q = 0; q <20; q++)
  			universe[i][q] = '#';
  	Random rng = new Random();
  	Path p = new Path(rng.nextInt(20),rng.nextInt(20), universe);
  	p.move();
  	System.out.println(p);
  	
  }
}