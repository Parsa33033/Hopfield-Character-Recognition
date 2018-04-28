import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main 
{
  //field
	private final static String FILENAME = "input.txt";
	private Map<String, Integer[][]> mapChars = new TreeMap<String, Integer[][]>();
	private List<String> characters = new ArrayList<String>();
	private List<String> fullCharacters = new ArrayList<String>();
	private Integer[][] mat ;
	private static int n = 0 ,m = 0 ,i = 0 ,j = 0;
	private final static double THRESHOLD = 0;
  //methods
	public static void main(String[] args)
	{
		Main main = new Main();
		main.getDim();
		main.getCharacter();
		Weight w = new Weight(n,m);
		
		for(int i = 0 ; i<main.fullCharacters.size() ; i++)
		{
			Hebb hebb = new Hebb(main.fullCharacters.get(i), main.mapChars.get(main.fullCharacters.get(i)), n, m, w);			
		}//for
		
		System.out.println("Weight Matrix derived by Hebb rule is");
		for(int i = 0 ; i<n*m ; i++)
		{
			System.out.println("Row "+i+" ===>  ");
			for(int j = 0 ; j<n*m ; j++)
			{
				System.out.print(w.getWeight()[i][j] + "\t");
			}
			System.out.println();
		}
		
		Hopfield hopfield = new Hopfield(n, m, w, THRESHOLD);
	
	}//main method
	
	
	public void getCharacter()
	{
		try
		{
			File file = new File(Main.class.getResource(FILENAME).toURI());
			FileInputStream fileInput = new FileInputStream(file);
			int c = 0;
			mat = new Integer[n][m];
			while((c = fileInput.read())!= -1)
			{
				getMatrix((char) c);
				if(((char)c) == '/')
				{
					StringBuilder str = new StringBuilder();
					while((c = fileInput.read()) != -1)
					{
						if((char)c == '\n')
						{
							break;
						}
						str.append((char)c);
					}
					String s = str.toString().replaceAll("[^A-Z0-9]", "");
					fullCharacters.add(s);
					if(!characters.contains(s.replaceAll("[0-9]", "")))
					{
						characters.add(s.replaceAll("[0-9]", ""));
					}
					mapChars.put(s, mat);
					mat = new Integer[n][m];
					i = 0; 
					j = 0;
				}//if / has reached
			}//while input is reading
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}//getCharacter method
	
	
	
	public void getMatrix(char c)
	{
		if(c == '#')
		{
			mat[i][j] = 1;
			j++;
		}//if
		else if(c == '*')
		{
			mat[i][j] = -1;
			j++;
		}//else
		
		if(j >= m)
		{
			j = 0;
			i++;
		}//if j>=m
		if(i >= n)
		{
			i = 0;
			j = 0;
		}//if i>=n
		
	}//getMatrix method
	
	
	
	public void getDim()
	{
		try
		{
			File file = new File(Main.class.getResource(FILENAME).toURI());
			FileInputStream fileInput = new FileInputStream(file);
			int c = 0;
			boolean end = false;
			while((char)(c = fileInput.read())!='/')
			{
				if(((char)c)=='\n')
				{
					n++;
					end = true;
				}//if reached next line
				else if(!end && (((char)c) == '#' || ((char)c) == '*'))
				{
					m++;
				}//else
			}//while
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}//getDim method
	
	
}
