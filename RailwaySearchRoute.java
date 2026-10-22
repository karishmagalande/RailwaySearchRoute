/*
 * Railway route planner for six cities in Maharashtra, India (console app).
 *
 * Admin menu: view routes sorted by distance, add or remove routes and cities.
 * User menu:  find a passenger train route (minimum spanning tree, Kruskal's
 *             algorithm) or an express route to Bhusaval Junction (shortest
 *             path, Dijkstra's algorithm).
 *
 * The admin login is a hard coded demo account (admin / admin).
 */
import java.util.*;

class Route
{
	String source,destination;
	int distance;
	//Route next;
	public Route() 
	{
		source=" ";
		destination=" ";
		distance=9999;
	}
	Route(String s,String d,int des)
	{
		source=s;
		destination=d;
		distance=des;
		//next=null;
	}
}
class RailwayRoute
{
	int predecessor[];
	int adjMat[][]=new int[30][30];
	static Scanner scanner=new Scanner(System.in);
	ArrayList<String> cities=new ArrayList<>();
	ArrayList<Route> routes=new ArrayList<>();
	public RailwayRoute()
	{
		//routes.add(new Route("Mumbai","Nagpur",873));
		routes.add(new Route("Pune","Mumbai",148));
		routes.add(new Route("Pune","Nashik",211));
		routes.add(new Route("Pune","Jalgaon",389));
		routes.add(new Route("Pune","Bhusaval",395));
		routes.add(new Route("Pune","Dhule",333));
		routes.add(new Route("Mumbai","Nashik",166));
		routes.add(new Route("Mumbai","Jalgaon",412));
		routes.add(new Route("Mumbai","Bhusaval",441));
		routes.add(new Route("Nashik","Dhule",158));
		routes.add(new Route("Nashik","Bhusaval",376));
		routes.add(new Route("Dhule","Jalgaon",91));
		routes.add(new Route("Jalgaon","Bhusaval",31));

		
		cities.add("Pune");
		cities.add("Mumbai");
		cities.add("Nashik");
		cities.add("Jalgaon");
		cities.add("Dhule");
		cities.add("Bhusaval");

	}
	
	public void removeCity()
	{
		System.out.print("Enter City: ");
		String city=scanner.next();
		if(cities.contains(city))
		{
			cities.remove(cities.indexOf(city));
			for (int i = 0; i < routes.size(); i++)
			{
				Route temp=routes.get(i);
				if(temp.source.equalsIgnoreCase(city) || temp.destination.equalsIgnoreCase(city))
				{
					routes.remove(i);
					i--;
				}
			}
			

			System.out.println("\nSOURCE\t\tDESTINATION\tDISTANCE");
			System.out.println("\n---------------------------------------------------------------------------");

			for (int i = 0; i < routes.size(); i++) 
			{
				Route t=routes.get(i);
				if(t.destination=="Bhusaval")
					System.out.println(t.source+"\t->\t"+t.destination+"\t"+t.distance);
				else
					System.out.println(t.source+"\t->\t"+t.destination+"\t   \t"+t.distance);

			}
		}
		else
		{
			System.out.println("...City Not Present...");
		}
	}
	public void removeRoute()
	{
		System.out.print("Enter Source: ");
		String src=scanner.next();
		System.out.print("Enter Destination:");
		String dest=scanner.next();
		for (int i = 0; i < routes.size(); i++)
		{
			Route temp=routes.get(i);
			if(temp.source.equalsIgnoreCase(src) && temp.destination.equalsIgnoreCase(dest))
			{
				routes.remove(i);
				i--;
			}
		}

		System.out.println("\nSOURCE\t\tDESTINATION\tDISTANCE");
		System.out.println("\n---------------------------------------------------------------------------");

		for (int i = 0; i < routes.size(); i++) 
		{
			Route t=routes.get(i);
			if(t.destination=="Bhusaval")
				System.out.println(t.source+"\t->\t"+t.destination+"\t"+t.distance);
			else
				System.out.println(t.source+"\t->\t"+t.destination+"\t   \t"+t.distance);
		}
	}

	Route sortedRoutes[];
	public void sort()
	{
		sortedRoutes=new Route[routes.size()];
		for(int i=0;i<routes.size();i++)
		{
			sortedRoutes[i]=routes.get(i);
		}
		//Sorting an Edge List
		Route temp=new Route();
		for(int i=0;i<routes.size();i++)
		{
			for(int j=i+1;j<routes.size();j++)
			{
				if(sortedRoutes[i].distance > sortedRoutes[j].distance)
				{
					if(sortedRoutes[i].source != sortedRoutes[j].source || sortedRoutes[i].destination!=sortedRoutes[j].destination)
					{
						temp.source=sortedRoutes[i].source;
						sortedRoutes[i].source=sortedRoutes[j].source;
						sortedRoutes[j].source=temp.source;
					
						temp.destination=sortedRoutes[i].destination;
						sortedRoutes[i].destination=sortedRoutes[j].destination;
						sortedRoutes[j].destination=temp.destination;
						
						temp.distance=sortedRoutes[i].distance;
						sortedRoutes[i].distance=sortedRoutes[j].distance;
						sortedRoutes[j].distance=temp.distance;
					}
					else
					{
						break;
					}
				}
			}
		}
				
		//Display Sorted Edge-List
		//System.out.println("Sorting of Routes according to Distance ");
		
		System.out.println("\nSOURCE\t\tDESTINATION\tDISTANCE");
		System.out.println("\n---------------------------------------------------------------------------");

		for(int i=0;i<sortedRoutes.length;i++)
		{
			if(sortedRoutes[i].destination=="Bhusaval")
			
				System.out.println(sortedRoutes[i].source+"\t->\t"+sortedRoutes[i].destination+"\t"+sortedRoutes[i].distance);
			else
				System.out.println(sortedRoutes[i].source+"\t->\t"+sortedRoutes[i].destination+"\t   \t"+sortedRoutes[i].distance);
		}
	}
	
	public void addRoute()
	{

		System.out.print("Enter Source: ");
		String s=scanner.next();
		System.out.print("Enter Destination: ");
		String d=scanner.next();
		System.out.print("Enter Distance: ");
		int dist=scanner.nextInt();
		System.out.println("\n---------------------------------------------------------------------------");
		if(cities.contains(s)==false)
		{
			cities.add(s);
		}
		else if(cities.contains(d)==false)
		{
			cities.add(d);
		}
		routes.add(new Route(s,d,dist));
		System.out.println("\nSOURCE\t\tDESTINATION\tDISTANCE");
		System.out.println("\n---------------------------------------------------------------------------");

		for (int i = 0; i < routes.size(); i++) 
		{
			Route t=routes.get(i);
			if(t.destination=="Bhusaval")
				System.out.println(t.source+"\t->\t"+t.destination+"\t"+t.distance);
			else
				System.out.println(t.source+"\t->\t"+t.destination+"\t   \t"+t.distance);

		}	
		System.out.println("\nCities are: \n"+cities);
	}
	
	public void addcity()
	{
		System.out.print("Enter city: ");
		String City=scanner.next();
		if(cities.contains(City)==false)
		{
			cities.add(City);
		}
		else
		{
			System.out.println("...City exists...");
		}
		System.out.println("\nCities are: \n"+cities);
	}
	
	public void searchRoute()
	{
		int choice;
		do 
		{
			System.out.println("\n\n========================================================================================");

			System.out.println("\t\t\t...MENU...");
			System.out.println("----------------------------------------------------------------------------------------");

			System.out.println("1. Passanger Train\n2. Express Train\n3. Back");
			System.out.println("========================================================================================");

			System.out.print("Enter your choice: ");
			choice=scanner.nextInt();
			switch(choice)
			{
			case 1:

				System.out.println("---------------------------------------------------------------------------");

				System.out.println("\n...PASSENGER TRAIN ROUTE...\n");
				System.out.println("---------------------------------------------------------------------------");

				System.out.println("\n...Available Routes...");
				kruskals();
				break;
			case 2:
				System.out.println("---------------------------------------------------------------------------");

				System.out.println("\n...EXPRESS TRAIN ROUTE... \n(ENDING POINT: BHUSAVAL JUNCTION)\n");
				System.out.println("---------------------------------------------------------------------------");

				//dijkstras();
				System.out.println("Enter source: ");
				String st=scanner.next();
				DijkstraRoute r=new DijkstraRoute();
				int start=r.cityRoute.indexOf(st);
				if(start<0)
					System.out.println("...City not present. Choose from "+r.cityRoute+"...");
				else
					r.djikstra(start);
				break;
			case 3:
				break;
				default:System.out.println("...INVALID CHOICE...");
			}
		} while (choice!=3);
	}
	int find(int i)
	{
		if(predecessor[i]==i)
			return i;
		else
			return find(predecessor[i]);
	}	
	void union(int s,int e)
	{
		predecessor[s]=predecessor[e];
	}
	public void kruskals()
	{
		predecessor=new int[(cities.size())];
		for (int i = 0; i < cities.size(); i++) 
			predecessor[i]=i;
		
		sort();
		
		int minDis=0;
	
		ArrayList<Route> result=new ArrayList<>();
	
		for(int i=0;i<sortedRoutes.length-1;i++)
		{
			Route temp=sortedRoutes[i];
			int iS=cities.indexOf(temp.source);
			int iD=cities.indexOf(temp.destination);

			int s=find(iS);
			int e=find(iD);

			if(s!=e)
			{
				result.add(temp);
				union(s,e);
				minDis=minDis+temp.distance;
			}
		}
		
		System.out.println("\n\nSelected cities for Passenger Train are:  \n");

		System.out.print("[ ");

		//Source(prev)==des(next)
		for (int i = result.size()-1; i >= 0; i--) 
		{
			Route temp1=result.get(i);
			for (int j = result.size()-1; j >= 0; j--) 
			{
				Route temp2=result.get(j);
				if(temp1.source.equalsIgnoreCase(temp2.destination))
				{
					System.out.print(temp2.source+" -> ");
				}
			}
		}
		
		System.out.print(result.get(0).source+" -> "+result.get(0).destination);
		System.out.print(" ]");
		System.out.println("\nDistance by Passenger Train:: "+minDis);
		
		
	}
	
}
public class RailwaySearchRoute
{
	private static Scanner scanner=RailwayRoute.scanner;   // one shared Scanner for all input
	public static void  main(String args[]) throws Exception
	{
		int choice,ch1;
		RailwayRoute rr=new RailwayRoute();
		do
		{
			System.out.println("\n\n\t\t\t  WELCOME");
			System.out.println("\n========================================================================================");
			System.out.println("\t\t\t...RAILWAY TRACK MANAGEMENT...");
			System.out.println("----------------------------------------------------------------------------------------");
			
			System.out.println("1. ADMIN\n2. USER\n3. EXIT");
			System.out.println("========================================================================================");

			System.out.print("\nEnter your choice: ");
			ch1=scanner.nextInt();
			//System.out.println("\n");
			switch(ch1)
			{
			case 1:
				
					System.out.println("----------------------------------------------------");
					System.out.println("...ADMIN LOGIN...");
					System.out.println("----------------------------------------------------");

					String password="admin";
					String username="admin";
					System.out.print("\nEnter Username: ");
					String user=scanner.next();
					System.out.print("Enter Password: ");
					String pass=scanner.next();
				
					if(user.compareTo(username)==0 && pass.compareTo(password)==0)
					{

						do 
						{
							System.out.println("\n_________________________________________________________________________");

							System.out.print("...Menu...\n");
							System.out.println("_________________________________________________________________________");
							System.out.println("\n1. Display by Distance\n2. Add Route\n3. Add City\n4. Remove Route\n5. Remove City\n6. Search Route\n7. Exit");
							System.out.println("\n_________________________________________________________________________");

							System.out.print("\nEnter your choice: ");
							choice=scanner.nextInt();
							//System.out.println("\n");
							
							switch(choice)
							{
							case 1:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...DISPALYING BY DISTANCE BY SORTING THE ROUTES...\n");
								System.out.println("---------------------------------------------------------------------------");
								rr.sort();
								break;
								
							case 2:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...ADDING ROUTE...\n");
								System.out.println("---------------------------------------------------------------------------");

								rr.addRoute();
								break;
								
							case 3:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...ADDING CITY...\n");
								System.out.println("---------------------------------------------------------------------------");

								rr.addcity();
								break;
								
							case 4:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...REMOVING ROUTE...\n");
								System.out.println("---------------------------------------------------------------------------");

								rr.removeRoute();
								break;

							case 5:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...REMOVING CITY...\n");
								System.out.println("---------------------------------------------------------------------------");

								rr.removeCity();
								break;

							case 6:
								System.out.println("---------------------------------------------------------------------------");

								System.out.println("\n...SEARCHING ROUTE ...\n");
								System.out.println("---------------------------------------------------------------------------");

								rr.searchRoute();
								break;

							case 7:
								System.out.println("...BACK...");
								break;
							default:
								System.out.println("...INVALID CHOICE...");
								break;
							}	
						}while (choice!=7);
					}
					else
					{
						System.out.println("Invalid Username and Password!!!");
					}
					break;	
				case 2:
					System.out.println("----------------------------------------------------");
					System.out.println("...USER DOMAIN...");
					System.out.println("----------------------------------------------------");
				//	System.out.println("\n...Available Routes...");
					
					rr.searchRoute();
					//DijkstraRoute r=new DijkstraRoute();
					//r.djikstra();	
					break;
				
				case 3:
					System.out.println("...THANK YOU...");
					break;
				
				default:
					System.out.println("...INVALID CHOICE...");
					break;		
			}
		}while(ch1!=3);
	}
}
