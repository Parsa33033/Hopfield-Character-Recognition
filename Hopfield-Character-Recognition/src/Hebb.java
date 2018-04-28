import java.util.Map;

public class Hebb 
{
  //field
	private Map<String, Integer> mapChars;
	private int n, m;
	private String character;
	private Integer[][] characterInput;
	private Weight weight;
  //methods
	
	
	public Hebb(String character, Integer[][] characterInput, int n, int m, Weight weight)
	{
		this.mapChars = mapChars;
		this.n = n;
		this.m = m;
		this.character = character;
		this.characterInput = characterInput;
		this.weight = weight;
		train();
	}//constructor
	
	
	public void train()
	{
		int[] input = getInput();
		for(int i = 0 ; i<input.length ; i++)
		{
			for(int j = 0 ; j<input.length ; j++)
			{
				if(i!=j)
				{
					weight.setWeight(input[i]*input[j], i, j);
					
				}//if
			}//for j
		
		}//for i
		
	}//train method
	
	
	
	public int[] getInput()
	{
		int[] input = new int[n*m];
		int z = 0;
		for(int i = 0 ; i<n ; i++)
		{
			for(int j = 0 ; j<m ; j++)
			{
				input[z] = characterInput[i][j];
				z++;
			}//for
		}//for
		return input;
	}//getInput method
	
	
}//Hebb class
