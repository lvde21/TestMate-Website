package edu.purdue.cs408.testmate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TestMateService {

	List<TestModuleForForm> testModuleList = TestModuleList.getInstance();
	static JsoupParser jsoupParser;

	
	public List<TestModuleForForm> getAllTestModuleForForms() {

		// TO DO : get the latest instance of the list
		testModuleList = jsoupParser.sendGetAllRequestToServer();
		return testModuleList;
	}


	public List<TestModuleForForm> searchTestModule(String name) {
		
		Comparator<TestModuleForForm> groupByComparator = Comparator.comparing(TestModuleForForm::getName);

		List<TestModuleForForm> result = testModuleList.stream().filter(e -> e.getName().equalsIgnoreCase(name))
										 .sorted(groupByComparator)
										 .collect(Collectors.toList());
		return result;							
	}


	public String addTestModuleForForm(TestModuleForForm testModuleForForm) {
		// should return the newly created TestModule
		//	testModuleList.add(testModuleForForm);
		jsoupParser.setModuleName(testModuleForForm.getName());
		jsoupParser.setProgramFiles(Arrays.asList(testModuleForForm.getExecutableFile()));
		jsoupParser.setTestFiles(Arrays.asList(testModuleForForm.getTestFile()));
		jsoupParser.setConfigFiles(Arrays.asList(testModuleForForm.getScriptFile()));

		jsoupParser.sendTestModuleToServer();

		// TO DO : get the latest instance of the list 
		return testModuleForForm.getName();
	}


	public void runTestModuleForForm(String name) {
		System.err.println("run test job for module name = " + name);
		jsoupParser.runModule(name);
	}

}