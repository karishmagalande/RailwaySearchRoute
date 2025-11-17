
import java.util.*;
class DijkstraRoute
{
	int pos;
	int pred[]=new int[20];
	ArrayList<String> cityRoute=new ArrayList<String>();
	int Matrix[][]=new int[6][6];
	int visited[]=new int[6];     //1=false and 0=true
	int dist[]=new int[6];
	
	//dist[0]=0;
	DijkstraRoute()
	{
		dist[0]=0;
		
		cityRoute.add(0,"Pune");
		cityRoute.add(1,"Mumbai");
		cityRoute.add(2,"Nashik");
		cityRoute.add(3,"Jalgaon");
		cityRoute.add(4,"Dhule");
		cityRoute.add(5,"Bhusaval");
		
		Matrix[0][0]=Integer.MAX_VALUE;
		Matrix[0][1]=148;
		Matrix[0][2]=211;
		Matrix[0][3]=389;
		Matrix[0][4]=333;
		Matrix[0][5]=395;
		
		Matrix[1][0]=148;
		Matrix[1][1]=Integer.MAX_VALUE;
		Matrix[1][2]=166;
		Matrix[1][3]=412;
		Matrix[1][4]=Integer.MAX_VALUE;
		Matrix[1][5]=441;
	
		Matrix[2][0]=211;
		Matrix[2][1]=166;
		Matrix[2][2]=Integer.MAX_VALUE;
		Matrix[2][3]=Integer.MAX_VALUE;
		Matrix[2][4]=158;
		Matrix[2][5]=276;
	
		Matrix[3][0]=389;
		Matrix[3][1]=412;
		Matrix[3][2]=Integer.MAX_VALUE;
		Matrix[3][3]=Integer.MAX_VALUE;
		Matrix[3][4]=91;
		Matrix[3][5]=31;
	
		Matrix[4][0]=333;
		Matrix[4][1]=Integer.MAX_VALUE;
		Matrix[4][2]=158;
		Matrix[4][3]=91;
		Matrix[4][4]=Integer.MAX_VALUE;
		Matrix[4][5]=Integer.MAX_VALUE;
	
		Matrix[5][0]=395;
		Matrix[5][1]=441;
		Matrix[5][2]=276;
		Matrix[5][3]=31;
		Matrix[5][4]=Integer.MAX_VALUE;
		Matrix[5][5]=Integer.MAX_VALUE;
	
		
	}
	
	int findMinDistance(int[] dist)
	{
		int min=9999;
		for(int i=0;i<cityRoute.size();i++)
		{
			if(visited[i]==0 && dist[i]<min )
			{
				min=dist[i];
				pos=i;
			}
		}
		return pos;
	}
	
	void djikstra(int st)
	{
		int i,j,k;
		int v=Matrix.length;
		for(i=0;i<v;i++)
		{
			pred[i]=-1;
			//visited[i]=0;
			dist[i]=Matrix[st][i];		
		}
		visited[st]=1;
		for(i=0;i<v;i++)
		{
			int minVertex=findMinDistance(dist);
			visited[minVertex]=1;
			
			for (j = 0; j < v; j++)
			{
				if (Matrix[minVertex][j]!=Integer.MAX_VALUE && dist[minVertex]!=Integer.MAX_VALUE && visited[j]==0)
				{
					int newDist=dist[minVertex]+Matrix[minVertex][j];
					if (newDist<dist[j]) 
					{
						dist[j]=newDist;
						pred[j]=minVertex;
					}
				}
			}
			
		}
		
		int resultant[]=new int[v+1];
		int in=v-1;
		resultant[0]=v-1;	
		for(i=0;i<v;i++)
		{
			resultant[i+1]=-1;
			if(pred[in]!=-1)
			{		
			//System.out.println(i+":: "+pred[i]);
			//System.out.println(cityRoute.get(pred[in]));
			resultant[i+1]=pred[in];
			in=pred[in];
			}
		//	else
			//	System.out.println("...City not present...");
		}
		resultant[i]=st;
		//System.out.println(cityRoute.get(st));
		for (int l = resultant.length-1; l >=0 ; l--) 
		{
			if(resultant[l]!=-1)
				System.out.print(" -> "+cityRoute.get(resultant[l]));
		}
		System.out.println();
	}
}