
public class Weight 
{
  //field
	private Integer[][] w;
  //methods
	public Weight(int n, int m)
	{
		w = new Integer[n*m][n*m];
		for(int i = 0 ; i<n*m ; i++)
		{
			for(int j = 0 ; j<n*m ; j++)
			{
				w[i][j] = 0;
			}
		}
	}//constructor
	
	
	public void setWeight(int value, int i, int j)
	{
		w[i][j] += value;
	}//setWeight method
	
	
	public Integer[][] getWeight()
	{
		return w;
	}//getWeight method
	
	
}//Weight class
