package edu.purdue.cs408.testmate;

import java.util.ArrayList;
import java.util.List;


public class TestModuleList {
	private static List<TestModuleForForm> testModuleList = new ArrayList();

	private TestModuleList() {}


	static {
		testModuleList.add(new TestModuleForForm("name1", "executableFile1", "testFile1", "scriptFile1", "latestTestJob1", "latestTestJobTimestamp1"));
		testModuleList.add(new TestModuleForForm("name2", "executableFile2", "testFile2", "scriptFile2", "latestTestJob2", "latestTestJobTimestamp2"));
		testModuleList.add(new TestModuleForForm("name3", "executableFile3", "testFile3", "scriptFile3", "latestTestJob3", "latestTestJobTimestamp3"));
	}


	public static List<TestModuleForForm> getInstance() {
		return testModuleList;
	}


	/* copy constructor */
	public static void replace(List<TestModuleForForm> newTestModuleList) {
		testModuleList = new ArrayList<TestModuleForForm>();
		for(TestModuleForForm tm : newTestModuleList) {
			testModuleList.add(tm);
		}
	}
}