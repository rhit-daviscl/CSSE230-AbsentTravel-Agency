import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	public void test() {
		Graph g = new Graph();
		g.addLocation("Chicago");
		g.addPath("Chicago","New York", 7, 7);
		g.addPath("Chicago","Seattle", 20, 18);
		g.addPath("Seattle","New York", 31, 30);
		g.addPath("Seattle", "Salt Lake", 10, 10);
		g.addPath("Salt Lake","Chicago", 8, 8);
		g.addPath("Salt Lake", "Boise", 4, 5);
		g.addPath("Salt Lake", "Ogden", 1, 1);
		g.addPath("Seattle", "Tacoma", 2, 2);
		System.out.println(g.citiesReachableTime("New York", 28));
		
		
		System.out.println(g);
		
	}

}
