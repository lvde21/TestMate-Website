package edu.purdue.cs408.testmate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "TestMateServlet",
        urlPatterns = {"/testmate"}
)

public class TestMateServlet extends HttpServlet {

	TestMateService testMateService = new TestMateService();

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if(action != null) {
        	searchTestModuleName(req, resp);
        } else {
        	List<TestModuleForForm> result = testMateService.getAllTestModuleForForms();
        	forwardTestModules(req, resp, result);
        }
    }


    private void searchTestModuleName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String testModuleName = req.getParameter("testModuleName");
        List<TestModuleForForm> result = testMateService.searchTestModule(testModuleName);
        forwardTestModules(req, resp, result);
    }


    public void forwardTestModules(HttpServletRequest req, HttpServletResponse resp, List testModuleList)
            throws ServletException, IOException {
            	String nextJSP = "/jsp/list-testmodules.jsp";
            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            	req.setAttribute("testModuleList", testModuleList);
            	dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
            	addTestModuleAction(req, resp);
            	break;
            case "run":
            	runTestModuleAction(req, resp);
            	break;
        }
    }

    private void addTestModuleAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String executableFile = req.getParameter("executableFile");
        String testFile = req.getParameter("testFile");
        String scriptFile = req.getParameter("scriptFile");  
        
        TestModuleForForm testModuleForForm = new TestModuleForForm(name, executableFile, testFile, scriptFile, "NA", "NA");
        
        testMateService.addTestModuleForForm(testModuleForForm);
        
        List<TestModuleForForm> testModuleList = testMateService.getAllTestModuleForForms();

        req.setAttribute("idTestModule", name);
        String message = "The new test module has been successfully created.";
        req.setAttribute("message", message);
        forwardTestModules(req, resp, testModuleList);  	

    }        	


    private void runTestModuleAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String testModuleName = req.getParameter("idTestModule");
        testMateService.runTestModuleForForm(testModuleName);

        List<TestModuleForForm> testModuleList = testMateService.getAllTestModuleForForms();
        req.setAttribute("idTestModule", testModuleName);
        String message = "The test module was run successfully.";
        req.setAttribute("message", message);
        forwardTestModules(req, resp, testModuleList); 
    }
}