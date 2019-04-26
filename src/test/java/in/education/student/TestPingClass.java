package in.education.student;

public class TestPingClass {

	public static void main(String[] args) {

		PingClass t = new PingClass();
		t.checkSites();

		System.out.println(t.siteStatus);

		t.close();
	}
}
