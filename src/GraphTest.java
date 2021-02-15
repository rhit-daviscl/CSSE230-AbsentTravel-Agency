import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	public void test() {
		Graph g = new Graph();
		g.addPath("Chicago", "South Bend", 70, 3);
		g.addPath("Chicago", "Indianapolis", 100, 4);
		g.addPath("South Bend", "Indianapolis", 120, 2);
		System.out.println(g);
		
	}

}
