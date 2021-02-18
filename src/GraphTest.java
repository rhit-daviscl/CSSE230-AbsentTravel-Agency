import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	public void test() {
		Graph g = new Graph();
		g.addLocation("Chicago");
		g.addPath("Chicago","New York", 7, 7);
	//	g.addPath("Chicago","Seattle", 18, 18);
		g.addPath("Seattle","New York", 100, 100);
	//	g.addPath("Seattle", "Salt Lake", 8, 10);
	//	g.addPath("Salt Lake","Chicago", 8, 8);
	//	g.addPath("Salt Lake", "Boise", 4, 5);
	//	g.addPath("Salt Lake", "Ogden", 1, 1);
		//g.addPath("Seattle", "Tacoma", 4, 2);
		//g.addPath("Salt Lake", "Tacoma", 7, 6);
		//g.addPath("Chicago", "Tacoma", 5, 5);
		g.addPath("Chicago", "Seattle", 4, 4);
		//System.out.println(g.citiesReachableTime("New York", 10000000));
		//System.out.println(g.citiesReachableDistance("Chicago", 80));
		
		
		//System.out.println("dist");
		//System.out.println(g.getShortestPathDistance("Chicago", "New York"));
		System.out.println(g.getShortestPathDistance("New York", "Seattle"));
		//System.out.println(g.getShortestPathDistance("New York", "Ogden"));
		//System.out.println(g.getShortestPathTime("New York", "Ogden"));
		
	}

}