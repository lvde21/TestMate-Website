package edu.purdue.cs408.testmate;

/*
 * TestModule object to take input from form and can be used for 
 * showing output on the landing page 
 * 
 */
public class TestModuleForForm {
    private String name;
    private String executableFile;
    private String testFile;
    private String scriptFile;
    private String latestTestJobName;
    private String latestTestJobTimestamp;
    private int totalTests;
    private int testsPassed;
    private int testsFailed;


    public TestModuleForForm(String name, String executableFile, String testFile, String scriptFile, String latestTestJobName, String latestTestJobTimestamp) {
        this.name = name;
        this.executableFile = executableFile;
        this.testFile = testFile;
        this.scriptFile = scriptFile;
        this.latestTestJobName = latestTestJobName;
        this.latestTestJobTimestamp = latestTestJobTimestamp;
        this.totalTests = 0;
        this.testsPassed = 0;
        this.testsFailed = 0;
    }

    public TestModuleForForm(String name, String latestTestJobName, String latestTestJobTimestamp, int testsPassed, int testsFailed, int totalTests) {
        this.name = name;
        this.latestTestJobName = latestTestJobName;
        this.latestTestJobTimestamp = latestTestJobTimestamp;
        this.testsPassed = testsPassed;
        this.testsFailed = testsFailed;
        this.totalTests = totalTests;
        this.executableFile = null;
        this.testFile = null;
        this.scriptFile = null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getExecutableFile() {
        return executableFile;
    }

    public void setExecutableFile(String executableFile) {
        this.executableFile = executableFile;
    }

    public String getTestFile() {
        return testFile;
    }

    public void setTestFile(String testFile) {
        this.testFile = testFile;
    }

    public String getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(String scriptFile) {
        this.scriptFile = scriptFile;
    }

    public String getLatestTestJobName() {
        return latestTestJobName;
    }

    public void setLatestTestJobName(String latestTestJobName) {
        this.latestTestJobName = latestTestJobName;
    }

    public String getLatestTestJobTimestamp() {
        return latestTestJobTimestamp;
    }

    public void setLatestTestJobTimestamp(String latestTestJobTimestamp) {
        this.latestTestJobTimestamp = latestTestJobTimestamp;
    }

    public int getTotalTests() { return totalTests; }
    public void setTotalTests(int totalTests) { this.totalTests = totalTests; }

    public int getTestsPassed() { return testsPassed; }
    public void setTestsPassed(int testsPassed) { this.testsPassed = testsPassed; }

    public int getTestsFailed() { return testsFailed; }
    public void setTestsFailed(int testsFailed) {this.testsFailed = testsFailed; }


    @Override
    public String toString() {
        return "TestModuleForForm{" + "name=" + name + ", executableFile=" + executableFile + 
                ", testFile=" + testFile + ", scriptFile=" + scriptFile + 
                ", latestTestJobName=" + latestTestJobName + ", latestTestJobTimestamp=" + 
                latestTestJobTimestamp + '}';
    }
}